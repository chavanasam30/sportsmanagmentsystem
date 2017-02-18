package com.experian.finance.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.experian.finance.dto.DatabaseConnection;
import com.experian.finance.dto.GetData;
import com.experian.finance.dto.InsertData;
import com.experian.finance.master.Contract;
import com.experian.finance.master.Slab;
import com.experian.finance.work.Input;
import com.experian.finance.work.Output;

public class FinanceWorking {

	
	float calculatedAmount;
	TreeMap<String, TreeMap<String, TreeMap<String, Contract>>> contractMap = new TreeMap<String, TreeMap<String, TreeMap<String, Contract>>>();
	private final String CUMULATIVE_SLAB_TYPE = "CUMULATIVE";
	private final String FIXED_SLAB_TYPE = "FIXED";
	Date currentDate = new Date();
	
	GetData getData = new GetData();

	InsertData insertData=new InsertData();
	
	TreeMap<String, TreeMap<String, TreeMap<String, Output>>> outputMap= new TreeMap<String, TreeMap<String, TreeMap<String, Output>>>();
	List<String> productIDList = new ArrayList<String>();
	
	List<String> productOutputIdList= new ArrayList<String>();
	
	String slabType;
	

	public FinanceWorking() {
		
		List<Contract> contractList = getData.getContract();
		TreeMap<String, TreeMap<String, Contract>> legalBUMap = null;
		TreeMap<String, Contract> productIdMap = null;
		for (Contract listedContract : contractList) {

			listedContract.nullifyDefaultDates();

			if (null == contractMap.get(listedContract.getLegalEntity() + "_"
					+ listedContract.getBusinessUnit())) {
				contractMap.put(listedContract.getLegalEntity() + "_"
						+ listedContract.getBusinessUnit(),
						new TreeMap<String, TreeMap<String, Contract>>());
			}
			legalBUMap = contractMap.get(listedContract.getLegalEntity() + "_"
					+ listedContract.getBusinessUnit());
			if (null == legalBUMap.get(listedContract.getClientId() + "_"
					+ listedContract.getClientLocationNo())) {
				legalBUMap.put(listedContract.getClientId() + "_"
						+ listedContract.getClientLocationNo(),
						new TreeMap<String, Contract>());
			}
			productIdMap = legalBUMap.get(listedContract.getClientId() + "_"
					+ listedContract.getClientLocationNo());
			if (null == productIdMap.get(listedContract.getProductId()))
				productIdMap.put(listedContract.getProductId(), listedContract);
			productIDList.add(listedContract.getProductId());
		}
	}

	private void calculateAdditionalCharges(Input input, Output output,
			Contract contract, boolean registerContract) {
		/**
		 * Check if the Contract is for Additional Services
		 */
		
		float additionalServiceCharges = contract.getAdditionalServiceCharges();
		calculatedAmount = input.getQuantity() * additionalServiceCharges;
		output.setQuantity(input.getQuantity());
		output.setAmount(calculatedAmount);
		output.setRate(additionalServiceCharges);
		output.setCharges("Additional Services");
		output.setDefaultText("Campaign:");
		input.setProcessResult("Additional service charges applied");
		if (registerContract) {
			insertData.updateContract("Additional_Service", contract);
		}
	}


	private void calculateCumulativeSlab(Input input, Output output,
			List<Slab> slabList) {
		for (Slab slab : slabList) {
			if ((slab.getMinSlabVolume() <= input.getQuantity()
					&& slab.getMaxSlabVolume() > input.getQuantity())
			|| (slab.getMinSlabVolume() < input.getQuantity()
					&& slab.getMaxSlabVolume() >= input.getQuantity())){
				calculatedAmount = input.getQuantity() * slab.getCharges();
				output.setQuantity(input.getQuantity());
				output.setRate(slab.getCharges());
				output.setAmount(calculatedAmount);
				return;
			}
		}
	}


	private void calculateFixedSlab(Input input, Output output,
			List<Slab> slabList) {
		calculatedAmount = 0;
		for (Slab slab : slabList) {
			if ((slab.getMinSlabVolume() <= input.getQuantity()
					&& slab.getMaxSlabVolume() > input.getQuantity())
			|| (slab.getMinSlabVolume() < input.getQuantity()
					&& slab.getMaxSlabVolume() >= input.getQuantity())){
				calculatedAmount = slab.getCharges();
				output.setQuantity(input.getQuantity());
				output.setAmount(calculatedAmount);
				output.setRate(calculatedAmount/input.getQuantity());
				return;
			}
		}
	}

	private void calculateDiscount(Input input, Output output, Contract contract, boolean registerContract) {

		/**
		 * Apply Discount Rates
		 */

		calculatedAmount = input.getQuantity() * contract.getDiscountRate();
		output.setQuantity(input.getQuantity());
		output.setRate(contract.getDiscountRate());
		output.setAmount(calculatedAmount);
		contract.setProcessedVolumeUpdateDate(input.getProcessDate());
		contract.setProcessedVolumeTillDate(contract
				.getProcessedVolumeTillDate() + input.getQuantity());
		if (registerContract) {
			insertData.updateContract("Discount", contract);
		}
	}

	private void calculateIncrementalSlab(Input input, Output output,
			List<Slab> slabList) {
		long processVolume = 0;
		calculatedAmount = 0;
		for (Slab slab : slabList) {
			if (slab.getMaxSlabVolume() <= input.getQuantity()) {
				processVolume = slab.getMaxSlabVolume()
						- slab.getMinSlabVolume();
				calculatedAmount += (processVolume * slab.getCharges());
			} else if (slab.getMaxSlabVolume() > input.getQuantity()
					&& slab.getMinSlabVolume() < input.getQuantity()) {
				processVolume = input.getQuantity() - slab.getMinSlabVolume();
				calculatedAmount += (processVolume * slab.getCharges());
			}
		}
		output.setQuantity(input.getQuantity());
		output.setAmount(calculatedAmount);
		output.setRate(calculatedAmount/input.getQuantity());
	}

	private void calculateNormalMailings(Input input, Output output,
			Contract contract, boolean registerContract) {

		Calendar calInput = Calendar.getInstance();
		Calendar calContract = Calendar.getInstance();
		calInput.setTime(input.getProcessDate());
		calContract.setTime(contract.getContractStartDate());

		int numberOfMonthsBetween = Math.abs(calInput.get(Calendar.YEAR)
				- calContract.get(Calendar.YEAR))
				* 12
				+ Math.abs(calInput.get(Calendar.MONTH)
						- calContract.get(Calendar.MONTH));

		contract.setProcessedVolumeUpdateDate(input.getProcessDate());
		
		if (null != contract.getAnnualSlabFlag()
				&& contract.getAnnualSlabFlag().equalsIgnoreCase("Y")) {
			if (numberOfMonthsBetween % 11 == 0 && numberOfMonthsBetween > 0) {
				long totalQuantityProcessed = contract
						.getProcessedVolumeAnnual() + input.getQuantity();
				if (contract.getMinAnnualSlabVolume() > totalQuantityProcessed) {
					calculatedAmount = (contract.getMinAnnualSlabVolume() - contract
							.getProcessedVolumeAnnual())
							* contract.getAnnualSlabRate();
					output.setAmount(calculatedAmount);
					output.setQuantity(contract.getMinAnnualSlabVolume() - contract
							.getProcessedVolumeAnnual());
					contract.setProcessedVolumeTillDate(totalQuantityProcessed);
					contract.setProcessedVolumeAnnual(0l);
					output.setRate(contract.getAnnualSlabRate());
					output.setDefaultText("Annual Trueup rate charges applied");
					input.setProcessResult("Annual Trueup rate charges applied");
					if (registerContract) {
						insertData.updateContract("AnnualSlab", contract);
					}
					return; 
				} else {
					contract.setProcessedVolumeAnnual(0l);
					contract.setProcessedVolumeTillDate(input.getQuantity());
					output.setDefaultText("Annual Trueup Quota Met");
					input.setProcessResult("Annual Trueup Quota Met");
					if (registerContract) {
						insertData.updateContract("AnnualSlab", contract);
					}
				}
			} else {
				contract.setProcessedVolumeAnnual(contract
						.getProcessedVolumeAnnual() + input.getQuantity());
				contract.setProcessedVolumeTillDate(input.getQuantity());
				if (registerContract) {
					insertData.updateContract("AnnualSlab", contract);
				}
			}
		}

		if (null != contract.getSemiAnnualSlabFlag()
				&& contract.getSemiAnnualSlabFlag().equalsIgnoreCase("Y")) {
			if (numberOfMonthsBetween % 5 == 0 && numberOfMonthsBetween > 0) {
				long totalQuantityProcessed = contract
						.getProcessedVolumeSemiAnnual() + input.getQuantity();
				if (contract.getMinSemiAnnualSlabVolume() > totalQuantityProcessed) {
					calculatedAmount = (contract.getMinSemiAnnualSlabVolume() - contract
							.getProcessedVolumeSemiAnnual())
							* contract.getSemiAnnualSlabRate();
					output.setAmount(calculatedAmount);
					output.setQuantity(contract.getMinSemiAnnualSlabVolume() - contract
							.getProcessedVolumeSemiAnnual());
					output.setRate(contract.getSemiAnnualSlabRate());
					contract.setProcessedVolumeTillDate(totalQuantityProcessed);
					contract.setProcessedVolumeSemiAnnual(0l);
					output.setDefaultText("Semi - Annual Trueup rate charges applied");
					input.setProcessResult("Semi - Annual Trueup rate charges applied");
					if (registerContract) {
						insertData.updateContract("SemiAnnualSlab", contract);
					}
					return;
				} else {
					contract.setProcessedVolumeSemiAnnual(0l);
					contract.setProcessedVolumeTillDate(input.getQuantity());
					output.setDefaultText("Semi Annual Trueup Quota Met");
					input.setProcessResult("Semi Annual Trueup Quota Met");
					if (registerContract) {
						insertData.updateContract("SemiAnnualSlab", contract);
					}
				}
			} else {
				contract.setProcessedVolumeSemiAnnual(contract
						.getProcessedVolumeSemiAnnual() + input.getQuantity());
				contract.setProcessedVolumeTillDate(input.getQuantity());
				if (registerContract) {
					insertData.updateContract("SemiAnnualSlab", contract);
				}
			}
		}

		if (null != contract.getQuarterlySlabFlag()
				&& contract.getQuarterlySlabFlag().equalsIgnoreCase("Y")) {
			if (numberOfMonthsBetween % 2 == 0 && numberOfMonthsBetween > 0) {
				long totalQuantityProcessed = contract
						.getProcessedVolumeQuarter() + input.getQuantity();
				if (contract.getMinQuarterlySlabVolume() > totalQuantityProcessed) {
					calculatedAmount = (contract.getMinQuarterlySlabVolume() - contract
							.getProcessedVolumeQuarter())
							* contract.getQuarterlyRate();
					output.setAmount(calculatedAmount);
					output.setQuantity(contract.getMinQuarterlySlabVolume() - contract
							.getProcessedVolumeQuarter());
					output.setRate(contract.getQuarterlyRate());
					contract.setProcessedVolumeTillDate(totalQuantityProcessed);
					contract.setProcessedVolumeQuarter(0l);
					output.setDefaultText("Quarterly Trueup rate charges applied");
					input.setProcessResult("Quarterly Trueup rate charges applied");
					if (registerContract) {
						insertData.updateContract("QuarterlySlab", contract);
					}
					return;
				} else {
					contract.setProcessedVolumeQuarter(0l);
					contract.setProcessedVolumeTillDate(input.getQuantity());
					output.setDefaultText("Quarterly Trueup Quota Met");
					input.setProcessResult("Quarterly Trueup Quota Met");
					if (registerContract) {
						insertData.updateContract("QuarterlySlab", contract);
					}
				}
			} else {
				contract.setProcessedVolumeQuarter(contract
						.getProcessedVolumeQuarter() + input.getQuantity());
				contract.setProcessedVolumeTillDate(input.getQuantity());
				if (registerContract) {
					insertData.updateContract("QuarterlySlab", contract);
				}
			}
		}
		
		if(null != contract.getMonthlySlabFlag() && contract.getMonthlySlabFlag().equalsIgnoreCase("Y")){
			long totalQuantityProcessed = contract.getProcessedVolumeTillDate() +input.getQuantity();
			if(contract.getMinmonthlySlabVolume() >= totalQuantityProcessed){
				calculatedAmount= contract.getMinmonthlySlabVolume() * contract.getMonthlyRate();
				output.setAmount(calculatedAmount);
				output.setQuantity(contract.getMinmonthlySlabVolume());
				output.setRate(contract.getMonthlyRate());
				contract.setProcessedVolumeTillDate(totalQuantityProcessed);
				output.setDefaultText("Monthly Trueup rate charges applied");
				input.setProcessResult("Monthly Trueup rate charges applied");
				if (registerContract) {
					insertData.updateContract("MonthlySlab", contract);
				}
				return;
			}else {
				contract.setProcessedVolumeTillDate(totalQuantityProcessed);
				if (registerContract) {
					insertData.updateContract("MonthlySlab", contract);
				}
			}
		}

		List<Slab> slabList = contract.getNormalSlabList();
		slabList = evaluateSlabForProcessDate(input, slabList);
		if (null != slabList && slabList.size() > 0) {
			if (isSlabListCumulative(slabList)) {
				calculateCumulativeSlab(input, output, slabList);
			} else if (isSlabListFixed(slabList)) {
				calculateFixedSlab(input, output, slabList);
			} else {
				calculateIncrementalSlab(input, output, slabList);
			}
			output.setQuantity(input.getQuantity());
			input.setProcessResult("Processed Successfully");
			contract.setProcessedVolumeTillDate(contract
					.getProcessedVolumeTillDate() + input.getQuantity());
		}else {
			output.setDefaultText("No Normal Slab defined in Contract");
			input.setProcessResult("No Normal Slab defined in Contract");
		}
		
		
	}

	private List<Slab> evaluateSlabForProcessDate(Input input, List<Slab> slabList) {
		ArrayList<Slab> slabSelected = new ArrayList<Slab>();
		if (null == slabList || slabList.size() == 0)
			return slabSelected;
		for(Slab slab : slabList){
			if (input.getProcessDate().before(slab.getSlabEndDate()) || input.getProcessDate().equals(slab.getSlabEndDate()))
				slabSelected.add(slab);
		}
		return slabSelected;
	}

	private void calculateOneTime(Input input, Output output, Contract contract, boolean registerContract) {
		/**
		 * Check if the Product & Input are for One Time Setup
		 */

		if (null != contract.getOneTimeSetupChargeDate()) {
			input.setProcessResult("Record already present and One time setup is done");
			output.setDefaultText("Duplicate entry for input detected.");
		} else {
			float oneTimeSetupfee = contract.getOneTimeSetupFee();
			contract.setOneTimeSetupFlag("Y");
			calculatedAmount = oneTimeSetupfee;
			contract.setOneTimeSetupChargeDate(input.getProcessDate());
			contract.setProcessedVolumeUpdateDate(input.getProcessDate());
			input.setProcessResult("Onetime setup charges applied");
			output.setAmount(calculatedAmount);
			output.setRate(contract.getOneTimeSetupFee());
			output.setCharges("ONE TIME SETUP");
			output.setDefaultText("ONE TIME SETUP");
			
			if (registerContract) {
				insertData.updateContract("ONE_TIME_SETUP", contract);
			}
		}
	}

	private void calculateSpecialRate(Input input, Output output,
			Contract contract, boolean registerContract) {
		/**
		 * Special Rate
		 */
		calculatedAmount = input.getQuantity() * contract.getSpecialRate();
		contract.setProcessedVolumeUpdateDate(input.getProcessDate());
		output.setQuantity(input.getQuantity());
		output.setRate(contract.getSpecialRate());
		output.setAmount(calculatedAmount);
		contract.setProcessedVolumeTillDate(contract
				.getProcessedVolumeTillDate() + input.getQuantity());
		input.setProcessResult("Special rate charges applied");
		
		if (registerContract) {
			insertData.updateContract("SpecialRate", contract);
		}
	}

	private void calculateTermination(Input input, Output output,
			Contract contract, boolean registerContract) {
		/**
		 * Check for Early Termination
		 */

		long quantity = input.getQuantity();

		float earlyTerminationRate = contract.getProrataCharges4Termination();

		long totalVolumeDeficit = contract.getEarlyTerminationMinimumVolume()
				- contract.getProcessedVolumeTillDate();
		if (totalVolumeDeficit >= quantity) {
			calculatedAmount = totalVolumeDeficit * earlyTerminationRate;
			output.setAmount(calculatedAmount);
			output.setQuantity(totalVolumeDeficit);
			contract.setProcessedVolumeTillDate(contract
					.getProcessedVolumeTillDate() + totalVolumeDeficit);
		} else if (totalVolumeDeficit < quantity) {
			calculatedAmount = quantity * earlyTerminationRate;
			output.setQuantity(quantity);
			output.setAmount(calculatedAmount);
			contract.setProcessedVolumeTillDate(contract
					.getProcessedVolumeTillDate() + quantity);
		}

		contract.setProcessedVolumeUpdateDate(input.getProcessDate());
		input.setProcessResult("Early termination charges applied");
		
		if (registerContract) {
			insertData.updateContract("EarlyTermination", contract);
		}
	}

	private void calculateWarmUp(Input input, Output output, Contract contract, boolean registerContract) {
		/**
		 * Calculate Warm Up Slabs
		 */

		List<Slab> slabList = contract.getWarmSlabList();
		slabList = evaluateSlabForProcessDate(input, slabList);
		if (null != slabList && slabList.size() > 0) {
			if (isSlabListCumulative(slabList)) {
				calculateCumulativeSlab(input, output, slabList);
			} else if (isSlabListFixed(slabList)) {
				calculateFixedSlab(input, output, slabList);
			} else {
				calculateIncrementalSlab(input, output, slabList);
			}
			input.setProcessResult("Processed Successfully");
			contract.setProcessedVolumeUpdateDate(input.getProcessDate());
			contract.setProcessedVolumeTillDate(contract
					.getProcessedVolumeTillDate() + input.getQuantity());
		} else {
			output.setDefaultText("No Warm Slab defined even though Warm Flag activated in Contract");
			input.setProcessResult("No Warm Slab defined even though Warm Flag activated in Contract");
		}
		
		if (registerContract) {
			insertData.updateContract("WarmUpSlabs", contract);
		}

	}

	public List<Output> processAndRegisterInput(List<Input> inputList) {
		List<Output> listOutput = new ArrayList<Output>();

		int i = 1;
		for (Input input : inputList) {
			Output output = new Output();

			String productId = input.getProductId();
			
			output.setSrNumber(i++);
			
			output.setLegalEntity(input.getLegalEntity());
			output.setBusinessUnit(input.getBusinessUnit());
			output.setClientId(input.getClientId());
			output.setClientName(input.getClientName());
			output.setClientLocationNo(input.getClientLocationNo());
			output.setNickName(input.getNickName());
			output.setTransactionType(input.getTransactionType());
			output.setCharges("Mailings");
			output.setDefaultText("Transaction Usage");
			output.setProcessDate(input.getProcessDate());
			output.setProductId(productId);
			if (null != input && input.getProcessDate() != null) {

				Contract contract = getContractForInput(input);

				if (null != contract
						&& null != contract.getProcessedVolumeUpdateDate()) {

					Calendar lastProcessedCalendarDate = Calendar.getInstance();
					Calendar currentCalendarDate = Calendar.getInstance();
					lastProcessedCalendarDate.setTime(contract
							.getProcessedVolumeUpdateDate());
					currentCalendarDate.setTime(input.getProcessDate());

					if (currentCalendarDate.get(Calendar.YEAR) == lastProcessedCalendarDate
							.get(Calendar.YEAR)
							&& currentCalendarDate.get(Calendar.MONTH) == lastProcessedCalendarDate
									.get(Calendar.MONTH)) {
						output.setProcessDate(null);
						input.setProcessResult("Record already been processed");
						output.setDefaultText("Duplicate entry for input detected.");
						continue;
					} else {
						if (contract.getProductId().equalsIgnoreCase(productId)) {
							productId = contract.getProductId();

							if (isOneTimeSetupApplicable(input, contract)) {
								calculateOneTime(input, output, contract, true);
							} else if (contract.getAdditionalServiceCharges() > 0) {
								calculateAdditionalCharges(input, output,
										contract, true);
							} else if (isEarlyTerminationApplicable(contract)) {
								calculateTermination(input, output, contract, true);
							} else if (isSpecialRateApplicable(input, contract)) {
								calculateSpecialRate(input, output, contract, true);

							} else if (isDiscountApplicable(input, contract)) {

								calculateDiscount(input, output, contract, true);

							} else if (isWarmUpApplicable(input, contract)) {
								calculateWarmUp(input, output, contract, true);
							} else {
								calculateNormalMailings(input, output, contract, true);
							}// else
						}
					}
				} else {
					output.setDefaultText("No Contract Entry found for the input");
					input.setProcessResult("No Contract Entry found for the input");
				}

			} // End of if (input.getProcessDate() != null)
			listOutput.add(output);
			insertData.insertInput(input);	
		} 

		generateOutputMapForLineNumber(listOutput);
		
		for(Output output : listOutput)
			insertData.insertOutput(output);				
		try {
			DatabaseConnection.getObject().getConnection().commit();
			DatabaseConnection.getObject().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOutput;
		
	}

	public List<Output> processInput(List<Input> inputList) {
		List<Output> listOutput = new ArrayList<Output>();

		int i = 1;
		for (Input input : inputList) {
			Output output = new Output();

			String productId = input.getProductId();
			
			output.setSrNumber(i++);
			
			output.setLegalEntity(input.getLegalEntity());
			output.setBusinessUnit(input.getBusinessUnit());
			output.setClientId(input.getClientId());
			output.setClientName(input.getClientName());
			output.setClientLocationNo(input.getClientLocationNo());
			output.setNickName(input.getNickName());
			output.setTransactionType(input.getTransactionType());
			output.setCharges("Mailings");
			output.setDefaultText("Transaction Usage");
			output.setProcessDate(input.getProcessDate());
			output.setProductId(productId);
			if (null != input && input.getProcessDate() != null) {

				Contract contract = getContractForInput(input);

				if (null != contract) {

					if (contract.getProductId().equalsIgnoreCase(productId)) {
						productId = contract.getProductId();

						if (isOneTimeSetupApplicable(input, contract)) {
							calculateOneTime(input, output, contract, false);
						} else if (contract.getAdditionalServiceCharges() > 0) {
							calculateAdditionalCharges(input, output,
									contract, false);
						} else if (isEarlyTerminationApplicable(contract)) {
							calculateTermination(input, output, contract, false);
						} else if (isSpecialRateApplicable(input, contract)) {
							calculateSpecialRate(input, output, contract, false);

						} else if (isDiscountApplicable(input, contract)) {

							calculateDiscount(input, output, contract, false);

						} else if (isWarmUpApplicable(input, contract)) {
							calculateWarmUp(input, output, contract, false);
						} else {
							calculateNormalMailings(input, output, contract, false);
						}// else
					}
				
				} else {
					output.setDefaultText("No Contract Entry found for the input");
					input.setProcessResult("No Contract Entry found for the input");
				}

			} // End of if (input.getProcessDate() != null)
			listOutput.add(output);
		} 

		generateOutputMapForLineNumber(listOutput);
		return listOutput;
		
	}
	
	private void generateOutputMapForLineNumber(List<Output> listOutput) {
		outputMap.clear();
		TreeMap<String, TreeMap<String, Output>> legalBUOutputMap=null;
		TreeMap<String, Output> productLineOutputMap=null;
		for(Output listedOutput: listOutput)
		{

			if (null == outputMap.get(listedOutput.getLegalEntity() + "_"
					+ listedOutput.getBusinessUnit())) {
				outputMap.put(listedOutput.getLegalEntity() + "_" + listedOutput.getBusinessUnit(), new TreeMap<String,TreeMap<String,Output>>());					
			}
		
			legalBUOutputMap = outputMap.get(listedOutput.getLegalEntity() + "_" + listedOutput.getBusinessUnit());
			
			if(null == legalBUOutputMap.get(listedOutput.getClientId() + "_" + listedOutput.getClientLocationNo())){
				legalBUOutputMap.put(listedOutput.getClientId() + "_" + listedOutput.getClientLocationNo(), new TreeMap<String, Output>());
			}
			
			productLineOutputMap= legalBUOutputMap.get(listedOutput.getClientId() + "_" + listedOutput.getClientLocationNo());
			
			if (null == productLineOutputMap.get(listedOutput.getProductId())) {
				productLineOutputMap.put(listedOutput.getProductId(), listedOutput);
			}
			
			listedOutput.setLine(""+productLineOutputMap.size());
		}
	}

	private Contract getContractForInput(Input input) {

		Contract requestedContract = null;
		TreeMap<String, TreeMap<String, Contract>> legalBUMap = null;
		TreeMap<String, Contract> productIdMap = null;

		if (null == contractMap.get(input.getLegalEntity() + "_"
				+ input.getBusinessUnit())) {
			return requestedContract;
		}
		legalBUMap = contractMap.get(input.getLegalEntity() + "_"
				+ input.getBusinessUnit());
		if (null == legalBUMap.get(input.getClientId() + "_"
				+ input.getClientLocationNo())) {
			return requestedContract;
		}
		productIdMap = legalBUMap.get(input.getClientId() + "_"
				+ input.getClientLocationNo());

		return productIdMap.get(input.getProductId());
	}

	private boolean isDiscountApplicable(Input input, Contract contract) {
		return null != contract.getDiscountRateFlag()
				&& "Y".equalsIgnoreCase(contract.getDiscountRateFlag())
				&& (input.getProcessDate()
						.before(contract.getDiscountEndDate()) 
						|| (null != contract
								.getDiscountExtensionFlag()
								&& contract.getDiscountExtensionFlag()
										.equalsIgnoreCase("Y") && input
								.getProcessDate().before(
										contract.getDiscountExtensionEndDate())
							)
					);
	}

	private boolean isEarlyTerminationApplicable(Contract contract) {
		return null != contract.getEarlyTerminationFlag()
				&& contract.getEarlyTerminationFlag().equalsIgnoreCase("Y");
	}

	private boolean isOneTimeSetupApplicable(Input input, Contract contract) {
		return input.getAccountSetupFlag().equalsIgnoreCase("Y")
				&& null != contract.getOneTimeSetupFlag()
				&& contract.getOneTimeSetupFlag().equalsIgnoreCase("Y");
	}

	private boolean isSlabListCumulative(List<Slab> slabList) {
		for (Slab aSlab : slabList)
			if (CUMULATIVE_SLAB_TYPE.equals(aSlab.getSlabType()))
				return true;
		return false;
	}

	private boolean isSlabListFixed(List<Slab> slabList) {
		for (Slab aSlab : slabList)
			if (FIXED_SLAB_TYPE.equals(aSlab.getSlabType()))
				return true;
		return false;
	}

	private boolean isSpecialRateApplicable(Input input, Contract contract) {
		return null != contract.getSpecialRateFlag()
				&& contract.getSpecialRateFlag().equalsIgnoreCase("Y")
				&& input.getProcessDate().before(contract.getSpecialEndDate());
	}

	private boolean isWarmUpApplicable(Input input, Contract contract) {
		return null != contract.getWarmupFlag()
				&& contract.getWarmupFlag().equalsIgnoreCase("Y")
				&& input.getProcessDate().before(contract.getWarmupEndDate())
				|| (null != contract.getWarmupExtensionFlag()
						&& contract.getWarmupExtensionFlag().equalsIgnoreCase(
								"Y") && input.getProcessDate().before(
						contract.getWarmupExtensionEndDate()));
	}
	
}
