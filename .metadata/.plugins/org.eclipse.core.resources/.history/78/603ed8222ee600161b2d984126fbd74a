/**
 * 
 */
package com.experian.finance.master;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author C02809A
 * 
 */
public class Contract {

	private String legalEntity;
	private String businessUnit;
	private Integer clientId;
	private String clientLocationNo;
	private String clientName;
	private String productId;
	private String productName;
	private String category;
	private Date contractStartDate;
	private Date contractEndDate;
	private Date arProcessDate;

	private String oneTimeSetupFlag;
	private Date oneTimeSetupChargeDate;
	private Float oneTimeSetupFee;

	private Float additionalServiceCharges;

	private String earlyTerminationFlag;
	private Date earlyTerminationDate;
	private Long earlyTerminationMinimumVolume;
	private Float prorataCharges4Termination;

	private Integer noMonthsOfCommitment;
	private Integer commitmentQty;

	private String discountRateFlag;
	private Date discountStartDate;
	private Date discountEndDate;
	private Float discountRate;
	private String discountExtensionFlag;
	private Date discountExtensionEndDate;

	private String specialRateFlag;
	private Date specialStartDate;
	private Date specialEndDate;
	private Float specialRate;

	private String warmupFlag;
	private Date warmupStartDate;
	private Date warmupEndDate;
	private String warmupExtensionFlag;
	private Date warmupExtensionEndDate;

	private String monthlySlabFlag;
	private Long minmonthlySlabVolume;
	private Long maxmonthlySlabVolume;
	private Float monthlyRate;

	private String quarterlySlabFlag;
	private Long minQuarterlySlabVolume;
	private Long maxQuarterlySlabVolume;
	private Float quarterlyRate;

	private String semiAnnualSlabFlag;
	private Long minSemiAnnualSlabVolume;
	private Long maxSemiAnnualSlabVolume;
	private Float semiAnnualSlabRate;

	private String annualSlabFlag;
	private Long minAnnualSlabVolume;
	private Long maxAnnualSlabVolume;
	private Float annualSlabRate;

	private Long processedVolumeTillDate;
	private Long processedVolumeQuarter;
	private Long processedVolumeSemiAnnual;
	private Long processedVolumeAnnual;

	private Date processedVolumeUpdateDate;

	private List<Slab> normalSlabList;
	private List<Slab> warmSlabList;

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientLocationNo() {
		return clientLocationNo;
	}

	public void setClientLocationNo(String clientLocationNo) {
		this.clientLocationNo = clientLocationNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getArProcessDate() {
		return arProcessDate;
	}

	public void setArProcessDate(Date arProcessDate) {
		this.arProcessDate = arProcessDate;
	}

	public String getOneTimeSetupFlag() {
		return oneTimeSetupFlag;
	}

	public void setOneTimeSetupFlag(String oneTimeSetupFlag) {
		this.oneTimeSetupFlag = oneTimeSetupFlag;
	}

	public Date getOneTimeSetupChargeDate() {
		return oneTimeSetupChargeDate;
	}

	public void setOneTimeSetupChargeDate(Date oneTimeSetupChargeDate) {
		this.oneTimeSetupChargeDate = oneTimeSetupChargeDate;
	}

	public Float getOneTimeSetupFee() {
		return oneTimeSetupFee;
	}

	public void setOneTimeSetupFee(Float oneTimeSetupFee) {
		this.oneTimeSetupFee = oneTimeSetupFee;
	}

	public Float getAdditionalServiceCharges() {
		return additionalServiceCharges;
	}

	public void setAdditionalServiceCharges(Float additionalServiceCharges) {
		this.additionalServiceCharges = additionalServiceCharges;
	}

	public String getEarlyTerminationFlag() {
		return earlyTerminationFlag;
	}

	public void setEarlyTerminationFlag(String earlyTerminationFlag) {
		this.earlyTerminationFlag = earlyTerminationFlag;
	}

	public Date getEarlyTerminationDate() {
		return earlyTerminationDate;
	}

	public void setEarlyTerminationDate(Date earlyTerminationDate) {
		this.earlyTerminationDate = earlyTerminationDate;
	}

	public Long getEarlyTerminationMinimumVolume() {
		return earlyTerminationMinimumVolume;
	}

	public void setEarlyTerminationMinimumVolume(
			Long earlyTerminationMinimumVolume) {
		this.earlyTerminationMinimumVolume = earlyTerminationMinimumVolume;
	}

	public Float getProrataCharges4Termination() {
		return prorataCharges4Termination;
	}

	public void setProrataCharges4Termination(Float prorataCharges4Termination) {
		this.prorataCharges4Termination = prorataCharges4Termination;
	}

	public Integer getNoMonthsOfCommitment() {
		return noMonthsOfCommitment;
	}

	public void setNoMonthsOfCommitment(Integer noMonthsOfCommitment) {
		this.noMonthsOfCommitment = noMonthsOfCommitment;
	}

	public Integer getCommitmentQty() {
		return commitmentQty;
	}

	public void setCommitmentQty(Integer commitmentQty) {
		this.commitmentQty = commitmentQty;
	}

	public String getDiscountRateFlag() {
		return discountRateFlag;
	}

	public void setDiscountRateFlag(String discountRateFlag) {
		this.discountRateFlag = discountRateFlag;
	}

	public Date getDiscountStartDate() {
		return discountStartDate;
	}

	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}

	public Date getDiscountEndDate() {
		return discountEndDate;
	}

	public void setDiscountEndDate(Date discountEndDate) {
		this.discountEndDate = discountEndDate;
	}

	public Float getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Float discountRate) {
		this.discountRate = discountRate;
	}

	public String getDiscountExtensionFlag() {
		return discountExtensionFlag;
	}

	public void setDiscountExtensionFlag(String discountExtensionFlag) {
		this.discountExtensionFlag = discountExtensionFlag;
	}

	public Date getDiscountExtensionEndDate() {
		return discountExtensionEndDate;
	}

	public void setDiscountExtensionEndDate(Date discountExtensionEndDate) {
		this.discountExtensionEndDate = discountExtensionEndDate;
	}

	public String getSpecialRateFlag() {
		return specialRateFlag;
	}

	public void setSpecialRateFlag(String specialRateFlag) {
		this.specialRateFlag = specialRateFlag;
	}

	public Date getSpecialStartDate() {
		return specialStartDate;
	}

	public void setSpecialStartDate(Date specialStartDate) {
		this.specialStartDate = specialStartDate;
	}

	public Date getSpecialEndDate() {
		return specialEndDate;
	}

	public void setSpecialEndDate(Date specialEndDate) {
		this.specialEndDate = specialEndDate;
	}

	public Float getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(Float specialRate) {
		this.specialRate = specialRate;
	}

	public String getWarmupFlag() {
		return warmupFlag;
	}

	public void setWarmupFlag(String warmupFlag) {
		this.warmupFlag = warmupFlag;
	}

	public Date getWarmupStartDate() {
		return warmupStartDate;
	}

	public void setWarmupStartDate(Date warmupStartDate) {
		this.warmupStartDate = warmupStartDate;
	}

	public Date getWarmupEndDate() {
		return warmupEndDate;
	}

	public void setWarmupEndDate(Date warmupEndDate) {
		this.warmupEndDate = warmupEndDate;
	}

	public String getWarmupExtensionFlag() {
		return warmupExtensionFlag;
	}

	public void setWarmupExtensionFlag(String warmupExtensionFlag) {
		this.warmupExtensionFlag = warmupExtensionFlag;
	}

	public Date getWarmupExtensionEndDate() {
		return warmupExtensionEndDate;
	}

	public void setWarmupExtensionEndDate(Date warmupExtensionEndDate) {
		this.warmupExtensionEndDate = warmupExtensionEndDate;
	}

	public String getMonthlySlabFlag() {
		return monthlySlabFlag;
	}

	public void setMonthlySlabFlag(String monthlySlabFlag) {
		this.monthlySlabFlag = monthlySlabFlag;
	}

	public Long getMinmonthlySlabVolume() {
		return minmonthlySlabVolume;
	}

	public void setMinmonthlySlabVolume(Long minmonthlySlabVolume) {
		this.minmonthlySlabVolume = minmonthlySlabVolume;
	}

	public Long getMaxmonthlySlabVolume() {
		return maxmonthlySlabVolume;
	}

	public void setMaxmonthlySlabVolume(Long maxmonthlySlabVolume) {
		this.maxmonthlySlabVolume = maxmonthlySlabVolume;
	}

	public Float getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(Float monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public String getQuarterlySlabFlag() {
		return quarterlySlabFlag;
	}

	public void setQuarterlySlabFlag(String quarterlySlabFlag) {
		this.quarterlySlabFlag = quarterlySlabFlag;
	}

	public Long getMinQuarterlySlabVolume() {
		return minQuarterlySlabVolume;
	}

	public void setMinQuarterlySlabVolume(Long minQuarterlySlabVolume) {
		this.minQuarterlySlabVolume = minQuarterlySlabVolume;
	}

	public Long getMaxQuarterlySlabVolume() {
		return maxQuarterlySlabVolume;
	}

	public void setMaxQuarterlySlabVolume(Long maxQuarterlySlabVolume) {
		this.maxQuarterlySlabVolume = maxQuarterlySlabVolume;
	}

	public Float getQuarterlyRate() {
		return quarterlyRate;
	}

	public void setQuarterlyRate(Float quarterlyRate) {
		this.quarterlyRate = quarterlyRate;
	}

	public String getSemiAnnualSlabFlag() {
		return semiAnnualSlabFlag;
	}

	public void setSemiAnnualSlabFlag(String semiAnnualSlabFlag) {
		this.semiAnnualSlabFlag = semiAnnualSlabFlag;
	}

	public Long getMinSemiAnnualSlabVolume() {
		return minSemiAnnualSlabVolume;
	}

	public void setMinSemiAnnualSlabVolume(Long minSemiAnnualSlabVolume) {
		this.minSemiAnnualSlabVolume = minSemiAnnualSlabVolume;
	}

	public Long getMaxSemiAnnualSlabVolume() {
		return maxSemiAnnualSlabVolume;
	}

	public void setMaxSemiAnnualSlabVolume(Long maxSemiAnnualSlabVolume) {
		this.maxSemiAnnualSlabVolume = maxSemiAnnualSlabVolume;
	}

	public Float getSemiAnnualSlabRate() {
		return semiAnnualSlabRate;
	}

	public void setSemiAnnualSlabRate(Float semiAnnualSlabRate) {
		this.semiAnnualSlabRate = semiAnnualSlabRate;
	}

	public String getAnnualSlabFlag() {
		return annualSlabFlag;
	}

	public void setAnnualSlabFlag(String annualSlabFlag) {
		this.annualSlabFlag = annualSlabFlag;
	}

	public Long getMinAnnualSlabVolume() {
		return minAnnualSlabVolume;
	}

	public void setMinAnnualSlabVolume(Long minAnnualSlabVolume) {
		this.minAnnualSlabVolume = minAnnualSlabVolume;
	}

	public Long getMaxAnnualSlabVolume() {
		return maxAnnualSlabVolume;
	}

	public void setMaxAnnualSlabVolume(Long maxAnnualSlabVolume) {
		this.maxAnnualSlabVolume = maxAnnualSlabVolume;
	}

	public Float getAnnualSlabRate() {
		return annualSlabRate;
	}

	public void setAnnualSlabRate(Float annualSlabRate) {
		this.annualSlabRate = annualSlabRate;
	}

	public Long getProcessedVolumeTillDate() {
		return processedVolumeTillDate;
	}

	public void setProcessedVolumeTillDate(Long processedVolumeTillDate) {
		this.processedVolumeTillDate = processedVolumeTillDate;
	}

	public Long getProcessedVolumeQuarter() {
		return processedVolumeQuarter;
	}

	public void setProcessedVolumeQuarter(Long processedVolumeQuarter) {
		this.processedVolumeQuarter = processedVolumeQuarter;
	}

	public Long getProcessedVolumeSemiAnnual() {
		return processedVolumeSemiAnnual;
	}

	public void setProcessedVolumeSemiAnnual(Long processedVolumeSemiAnnual) {
		this.processedVolumeSemiAnnual = processedVolumeSemiAnnual;
	}

	public Long getProcessedVolumeAnnual() {
		return processedVolumeAnnual;
	}

	public void setProcessedVolumeAnnual(Long processedVolumeAnnual) {
		this.processedVolumeAnnual = processedVolumeAnnual;
	}

	public Date getProcessedVolumeUpdateDate() {
		return processedVolumeUpdateDate;
	}

	public void setProcessedVolumeUpdateDate(Date processedVolumeUpdateDate) {
		this.processedVolumeUpdateDate = processedVolumeUpdateDate;
	}

	public List<Slab> getNormalSlabList() {
		return normalSlabList;
	}

	public void setNormalSlabList(List<Slab> normalSlabList) {
		this.normalSlabList = normalSlabList;
	}

	public List<Slab> getWarmSlabList() {
		return warmSlabList;
	}

	public void setWarmSlabList(List<Slab> warmSlabList) {
		this.warmSlabList = warmSlabList;
	}

	public Contract() {
	}

	public Contract(String[] ar) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
			legalEntity = ar[0];
			businessUnit = ar[1];
			clientId = ar[2].length() == 0 ? null : Integer.parseInt(ar[2]);
			clientLocationNo = ar[3];
			clientName = ar[4];
			productId = ar[5];
			productName = ar[6];
			category = ar[7];
			contractStartDate = ar[8].length() == 0 ? null : sdf.parse(ar[8]);
			contractEndDate = ar[9].length() == 0 ? null : sdf.parse(ar[9]);
			arProcessDate = ar[10].length() == 0 ? null : sdf.parse(ar[10]);
			oneTimeSetupFlag = ar[11];
			oneTimeSetupChargeDate = ar[12].length() == 0 ? null : sdf
					.parse(ar[12]);
			oneTimeSetupFee = ar[13].length() == 0 ? null : Float
					.parseFloat(ar[13]);
			additionalServiceCharges = ar[14].length() == 0 ? null : Float
					.parseFloat(ar[14]);
			earlyTerminationFlag = ar[15];
			earlyTerminationDate = ar[16].length() == 0 ? null : sdf
					.parse(ar[16]);
			earlyTerminationMinimumVolume = ar[17].length() == 0 ? null : Long
					.parseLong(ar[17]);
			prorataCharges4Termination = ar[18].length() == 0 ? null : Float
					.parseFloat(ar[18]);
			noMonthsOfCommitment = ar[19].length() == 0 ? null : Integer
					.parseInt(ar[19]);
			commitmentQty = ar[20].length() == 0 ? null : Integer
					.parseInt(ar[20]);
			discountRateFlag = ar[21];
			discountStartDate = ar[22].length() == 0 ? null : sdf.parse(ar[22]);
			discountEndDate = ar[23].length() == 0 ? null : sdf.parse(ar[23]);
			discountRate = ar[24].length() == 0 ? null : Float
					.parseFloat(ar[24]);
			discountExtensionFlag = ar[25];
			discountExtensionEndDate = ar[26].length() == 0 ? null : sdf
					.parse(ar[26]);
			specialRateFlag = ar[27];
			specialStartDate = ar[28].length() == 0 ? null : sdf.parse(ar[28]);
			specialEndDate = ar[29].length() == 0 ? null : sdf.parse(ar[29]);
			specialRate = ar[30].length() == 0 ? null : Float
					.parseFloat(ar[30]);
			warmupFlag = ar[31];
			warmupStartDate = ar[32].length() == 0 ? null : sdf.parse(ar[32]);
			warmupEndDate = ar[33].length() == 0 ? null : sdf.parse(ar[33]);
			warmupExtensionFlag = ar[34];
			warmupExtensionEndDate = ar[35].length() == 0 ? null : sdf
					.parse(ar[35]);
			monthlySlabFlag = ar[36];
			minmonthlySlabVolume = (ar[37].equals("") || ar[37].length() == 0) ? null : Long
					.parseLong(ar[37]);
			maxmonthlySlabVolume = ar[38].length() == 0 ? null : Long
					.parseLong(ar[38]);
			monthlyRate = ar[39].length() == 0 ? null : Float
					.parseFloat(ar[39]);
			quarterlySlabFlag = ar[40];
			minQuarterlySlabVolume = ar[41].length() == 0 ? null : Long
					.parseLong(ar[41]);
			maxQuarterlySlabVolume = ar[42].length() == 0 ? null : Long
					.parseLong(ar[42]);
			quarterlyRate = ar[43].length() == 0 ? null : Float
					.parseFloat(ar[43]);
			semiAnnualSlabFlag = ar[44];
			minSemiAnnualSlabVolume = ar[45].length() == 0 ? null : Long
					.parseLong(ar[45]);
			maxSemiAnnualSlabVolume = ar[46].length() == 0 ? null : Long
					.parseLong(ar[46]);
			semiAnnualSlabRate = ar[47].length() == 0 ? null : Float
					.parseFloat(ar[47]);
			annualSlabFlag = ar[48];
			
			minAnnualSlabVolume = ar[49].length() == 0 ? null : Long
					.parseLong(ar[49]);
			maxAnnualSlabVolume = ar[50].length() == 0 ? null : Long
					.parseLong(ar[50]);
			annualSlabRate = ar[51].length() == 0 ? null : Float
					.parseFloat(ar[51]);
			processedVolumeTillDate = ar[52].length() == 0 ? null : Long
					.parseLong(ar[52]);
			processedVolumeQuarter = ar[53].length() == 0 ? null : Long
					.parseLong(ar[53]);
			processedVolumeSemiAnnual = ar[54].length() == 0 ? null : Long
					.parseLong(ar[54]);
			processedVolumeAnnual = ar[55].length() == 0 ? null : Long
					.parseLong(ar[55]);
			processedVolumeUpdateDate = ar[56].length() == 0 ? null : sdf
					.parse(ar[56]);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void populateNullFields() {
		try {
			Class<Contract> aClass = Contract.class;
			Method[] methodList = aClass.getDeclaredMethods();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date defaultDate = sdf.parse("01-02-0001");

			for (Method fi : methodList) {
				if (StringUtils.startsWith(fi.getName(), "set"))
					continue;
				if (fi.getReturnType().equals(String.class)) {
					String ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (String) fi.invoke(this, new Object[] {});

					if (ob == null)
						continue;
					if (ob.length() == 0) {
						Method setter = aClass.getMethod("set"
								+ callingMethodName,
								new Class<?>[] { String.class });
						if (setter != null)
							// Setter to trim and set the trimmed String value
							setter.invoke(this, new Object[] { null });

					}

				}
				if (fi.getReturnType().equals(Date.class)) {
					Date ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Date) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Date.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { defaultDate });

				}
				if (fi.getReturnType().equals(Long.class)) {
					Long ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Long) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Long.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { new Long(-1) });

				}
				if (fi.getReturnType().equals(Integer.class)) {
					Integer ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Integer) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Integer.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { new Integer(-1) });

				}
				if (fi.getReturnType().equals(Float.class)) {
					Float ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Float) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Float.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { new Float(-1) });

				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void nullifyDefaultDates() {

		try {
			Class<Contract> aClass = Contract.class;
			Method[] methodList = aClass.getDeclaredMethods();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			for (Method fi : methodList) {
				if (StringUtils.startsWith(fi.getName(), "set"))
					continue;

				if (fi.getReturnType().equals(Date.class)) {
					Date ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Date) fi.invoke(this, new Object[] {});

					if (ob == null || !"01-02-0001".equals(sdf.format(ob)))
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Date.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { null });

				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {

		String item = legalEntity + "," + businessUnit + "," + clientId + ","
				+ clientLocationNo + "," + clientName + "," + productId + ","
				+ productName + "," + category + "," + contractStartDate + ","
				+ contractEndDate + "," + arProcessDate + ","
				+ oneTimeSetupFlag + "," + oneTimeSetupChargeDate + ","
				+ oneTimeSetupFee + "," + additionalServiceCharges + ","
				+ earlyTerminationFlag + "," + earlyTerminationDate + ","
				+ earlyTerminationMinimumVolume + ","
				+ prorataCharges4Termination + "," + noMonthsOfCommitment + ","
				+ commitmentQty + "," + discountRateFlag + ","
				+ discountStartDate + "," + discountEndDate + ","
				+ discountRate + "," + discountExtensionFlag + ","
				+ discountExtensionEndDate + "," + specialRateFlag + ","
				+ specialStartDate + "," + specialEndDate + "," + specialRate
				+ "," + warmupFlag + "," + warmupStartDate + ","
				+ warmupEndDate + "," + warmupExtensionFlag + ","
				+ warmupExtensionEndDate + "," + monthlySlabFlag + ","
				+ minmonthlySlabVolume + "," + maxmonthlySlabVolume + ","
				+ monthlyRate + "," + quarterlySlabFlag + ","
				+ minQuarterlySlabVolume + "," + maxQuarterlySlabVolume + ","
				+ quarterlyRate + "," + semiAnnualSlabFlag + ","
				+ minSemiAnnualSlabVolume + "," + maxSemiAnnualSlabVolume + ","
				+ semiAnnualSlabRate + "," + annualSlabFlag + ","
				+ minAnnualSlabVolume + "," + maxAnnualSlabVolume + ","
				+ annualSlabRate + "," + processedVolumeTillDate + ","
				+ processedVolumeQuarter + "," + processedVolumeSemiAnnual
				+ "," + processedVolumeAnnual + "," + processedVolumeUpdateDate;

		return item;
	}

}
