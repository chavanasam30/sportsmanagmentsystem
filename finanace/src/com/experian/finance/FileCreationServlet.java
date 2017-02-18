package com.experian.finance;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.experian.finance.dto.DatabaseConnection;
import com.experian.finance.dto.GetData;
import com.experian.finance.dto.InsertData;
import com.experian.finance.master.Contract;
/**
 * Servlet implementation class FileCreationServlet
 */

public class FileCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FileCreationServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected Float getFloat(String value){
		if(value.trim().equals(""))
			return new Float("0.0");	
		return new Float(value);
	}
	protected Long getLong(String value){
		if(value.trim().equals(""))
			return new Long(0);	
		return new Long(value);
	}
	protected Integer getInteger(String value){
		if(value.trim().equals(""))
			return new Integer("0");	
		return new Integer(value);
	}
	//protected java.sql.Date getDate(String date){
	protected Date getDate(String date){
		Date dateParsed;
		try {
			System.out.println("date"+date);
		if(date.trim().equalsIgnoreCase(""))
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
			dateParsed = sdf.parse("01-Feb-0001");
			return dateParsed;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		dateParsed = sdf.parse(date);
		System.out.println("date1"+dateParsed);
		
		return dateParsed;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private Contract setter(Contract objContract,JSONObject jsonObject) throws JSONException{
		objContract.setLegalEntity(jsonObject.get("LEGAL_ENTITY").toString());
		objContract.setBusinessUnit(jsonObject.get("BUSINESS_UNIT").toString());
		objContract.setClientId(getInteger(jsonObject.get("CLIENT_ID").toString()));
		objContract.setClientLocationNo(jsonObject.get("CLIENT_LOCATION_NO").toString());
		objContract.setClientName(jsonObject.get("CLIENT_NAME").toString());
		objContract.setProductId(jsonObject.get("PRODUCT_ID").toString());
		objContract.setProductName(jsonObject.get("PRODUCT_NAME").toString());
		objContract.setCategory(jsonObject.get("CATEGORY").toString());
		objContract.setContractStartDate(getDate(jsonObject.get("CONTRACT_START_DATE").toString()));
		objContract.setContractEndDate(getDate(jsonObject.get("CONTRACT_END_DATE").toString()));
		objContract.setArProcessDate(getDate(jsonObject.get("AR_PROCESS_DATE").toString()));
		objContract.setOneTimeSetupFlag(jsonObject.get("ONE_TIME_SETUP_FLAG").toString());
		objContract.setOneTimeSetupChargeDate(getDate(jsonObject.get("ONE_TIME_SETUP_CHARGE_DATE").toString()));
		objContract.setOneTimeSetupFee(getFloat(jsonObject.get("ONE_TIME_SETUP_FEE").toString()));
		objContract.setAdditionalServiceCharges(getFloat(jsonObject.get("ADDITIONAL_SERVICE_CHARGES").toString()));
		objContract.setEarlyTerminationFlag(jsonObject.get("EARLY_TERMINATION_FLAG").toString());
		objContract.setEarlyTerminationDate(getDate(jsonObject.get("EARLY_TERMINATION_DATE").toString()));
		objContract.setEarlyTerminationMinimumVolume(getLong(jsonObject.get("EARLY_TERMINATION_MINIMUM_VOLUME").toString()));
		objContract.setProrataCharges4Termination(getFloat(jsonObject.get("PRO_RATA_CHARGES_FOR_TERMINATION").toString()));
		objContract.setNoMonthsOfCommitment(getInteger(jsonObject.get("NO_MONTHS_OF_COMMITMENT").toString()));
		objContract.setCommitmentQty(getInteger(jsonObject.get("COMMITMENT_QTY").toString()));
		objContract.setDiscountRateFlag(jsonObject.get("DISCOUNT_RATE_FLAG").toString());
		objContract.setDiscountStartDate(getDate(jsonObject.get("DISCOUNT_START_DATE").toString()));
		objContract.setDiscountEndDate(getDate(jsonObject.get("DISCOUNT_END_DATE").toString()));
		objContract.setDiscountRate(getFloat(jsonObject.get("DISCOUNT_RATE").toString()));
		objContract.setDiscountExtensionFlag(jsonObject.get("DISCOUNT_EXTENSION_FLAG").toString());
		objContract.setDiscountExtensionEndDate(getDate(jsonObject.get("DISCOUNT_EXTENSION_END_DATE").toString()));
		objContract.setSpecialRateFlag(jsonObject.get("SPECIAL_RATE_FLAG").toString());
		objContract.setSpecialStartDate(getDate(jsonObject.get("SPECIAL_START_DATE").toString()));
		objContract.setSpecialEndDate(getDate(jsonObject.get("SPECIAL_END_DATE").toString()));
		objContract.setSpecialRate(getFloat(jsonObject.get("SPECIAL_RATE").toString()));
		objContract.setWarmupFlag(jsonObject.get("WARM_UP_FLAG").toString());
		objContract.setWarmupStartDate(getDate(jsonObject.get("WARM_UP_START_DATE").toString()));
		objContract.setWarmupEndDate(getDate(jsonObject.get("WARM_UP_END_DATE").toString()));
		objContract.setWarmupExtensionFlag(jsonObject.get("WARM_UP_EXTENSION_FLAG").toString());
		objContract.setWarmupExtensionEndDate(getDate(jsonObject.get("WARM_UP_EXTENSION_END_DATE").toString()));
		objContract.setMonthlySlabFlag(jsonObject.get("MONTHLY_SLAB_FLAG").toString());
		objContract.setMinmonthlySlabVolume(getLong(jsonObject.get("MIN_MONTHLY_SLAB_VOLUME").toString()));
		objContract.setMaxmonthlySlabVolume(getLong(jsonObject.get("MAX_MONTHLY_SLAB_VOLUME").toString()));
		objContract.setMonthlyRate(getFloat(jsonObject.get("MONTHLY_RATE").toString()));
		objContract.setQuarterlySlabFlag(jsonObject.get("QUARTERLY_SLAB_FLAG").toString());
		objContract.setMinQuarterlySlabVolume(getLong(jsonObject.get("MIN_QUARTERLY_SLAB_VOLUME").toString()));
		objContract.setMaxQuarterlySlabVolume(getLong(jsonObject.get("MAX_QUARTERLY_SLAB_VOLUME").toString()));
		objContract.setQuarterlyRate(getFloat(jsonObject.get("QUARTERLY_RATE").toString()));
		objContract.setSemiAnnualSlabFlag(jsonObject.get("SEMI_ANNUAL_SLAB_FLAG").toString());
		objContract.setMinSemiAnnualSlabVolume(getLong(jsonObject.get("MIN_SEMI_ANNUAL_SLAB_VOLUME").toString()));
		objContract.setMaxAnnualSlabVolume(getLong(jsonObject.get("MAX_SEMI_ANNUAL_SLAB_VOLUME").toString()));
		objContract.setSemiAnnualSlabRate(getFloat(jsonObject.get("SEMI_ANNUAL_SLAB_RATE").toString()));
		objContract.setAnnualSlabFlag(jsonObject.get("ANNUAL_SLAB_FLAG").toString());
		objContract.setMinAnnualSlabVolume(getLong(jsonObject.get("MIN_ANNUAL_SLAB_VOLUME").toString()));
		objContract.setMaxAnnualSlabVolume(getLong(jsonObject.get("MAX_ANNUAL_SLAB_VOLUME").toString()));
		objContract.setAnnualSlabRate(getFloat(jsonObject.get("ANNUAL_SLAB_RATE").toString()));
		objContract.setProcessedVolumeTillDate(getLong(jsonObject.get("PROCESSED_VOLUME_TILL_DATE").toString()));
		objContract.setProcessedVolumeQuarter(getLong(jsonObject.get("PROCESSED_VOLUME_QUARTER").toString()));
		objContract.setProcessedVolumeSemiAnnual(getLong(jsonObject.get("PROCESSED_VOLUME_SEMI_ANNUAL").toString()));
		objContract.setProcessedVolumeAnnual(getLong(jsonObject.get("PROCESSED_VOLUME_ANNUAL").toString()));
		objContract.setProcessedVolumeUpdateDate(getDate(jsonObject.get("PROCESSED_VOLUME_UPDATE_DATE").toString()));
		return objContract;
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonRes=new JSONObject();
		PrintWriter out = response.getWriter();
		
			
	        response.setContentType("text/html");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	 
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setHeader("Access-Control-Max-Age", "86400");
	 
			
			JSONObject jsonObject;
			try {
				
				jsonObject = new JSONObject(request.getParameter("dataSend"));
			
			String legalEntity= jsonObject.getString("LEGAL_ENTITY");
			String businessUnit= jsonObject.getString("BUSINESS_UNIT");
			int clientId= Integer.parseInt(jsonObject.getString("CLIENT_ID"));
			String clientLocationNo = jsonObject.getString("CLIENT_LOCATION_NO");
			String productId = jsonObject.getString("PRODUCT_ID");
			
		//	System.out.println("DataSend : "+legalEntity +" "+ businessUnit+" "+clientId+" "+clientLocationNo+" "+productId);
			
			String selectString = "select * from FIN_CONTRACT"
					+ " where LEGAL_ENTITY = '"+legalEntity+"' AND BUSINESS_UNIT = '"+businessUnit+"' AND "
					+ "CLIENT_ID = "+clientId+" AND CLIENT_LOCATION_NO = '"+clientLocationNo+"' AND PRODUCT_ID ='"+productId+"'";
			
			boolean recordExist = new GetData().recordExist(selectString);
			if(recordExist){
				jsonRes.put("status","Already");
				System.out.println("json put if" +jsonRes);
				 
			}else{
				Contract objContract=setter(new Contract(),jsonObject);
				objContract.populateNullFields();	
				new InsertData().insertContract(objContract);
				jsonRes.put("status","Inserted" );
				System.out.println("success");
		}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				System.out.println("Error");
			}	
			System.out.println("jsonRes.toString()" + jsonRes.toString());
			
			out.write(jsonRes.toString());
			//println(jsonRes); 
			//out.close();
	}
	

}
