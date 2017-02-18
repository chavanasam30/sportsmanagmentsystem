package com.experian.finance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.experian.finance.master.Slab;

@SuppressWarnings("serial")
public class InsertSlabData extends HttpServlet{

	public InsertSlabData()
	{
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
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
	private Slab setter(Slab slabObject,JSONObject jsonObject) throws JSONException{
		slabObject.setLegalEntity(jsonObject.get("LEGAL_ENTITY").toString());
		slabObject.setBusinessUnit(jsonObject.get("BUSINESS_UNIT").toString());
		slabObject.setClientId(getInteger(jsonObject.get("CLIENT_ID").toString()));
		slabObject.setClientLocationNo(jsonObject.get("CLIENT_LOCATION_NO").toString());
		slabObject.setClientName(jsonObject.get("CLIENT_NAME").toString());
		slabObject.setProductId(jsonObject.get("PRODUCT_ID").toString());
		slabObject.setSlabCategory(jsonObject.get("SLAB_CATEGORY").toString());
		slabObject.setSlabType(jsonObject.get("SLAB_TYPE").toString());
		slabObject.setMinSlabVolume(Long.parseLong(jsonObject.get("MIN_SLAB_VOLUME").toString()));
		slabObject.setMaxSlabVolume(Long.parseLong(jsonObject.get("MAX_SLAB_VOLUME").toString()));
		slabObject.setCharges(Float.parseFloat(jsonObject.get("CHARGES").toString()));
		slabObject.setActive(Integer.parseInt(jsonObject.get("ACTIVE").toString()));
		slabObject.setSlabEndDate(getDate(jsonObject.get("SLAB_END_DATE").toString()));
		return slabObject;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
	        try
	        {
		        jsonObject = new JSONObject(request.getParameter("dataSend"));
				
				String legalEntity= jsonObject.getString("LEGAL_ENTITY");
				String businessUnit= jsonObject.getString("BUSINESS_UNIT");
				int clientId= Integer.parseInt(jsonObject.getString("CLIENT_ID"));
				String clientLocationNo = jsonObject.getString("CLIENT_LOCATION_NO");
				String productId = jsonObject.getString("PRODUCT_ID");
				
		        String selectString = "select * from FIN_CONTRACT_SLAB"
						+ " where LEGAL_ENTITY = '"+legalEntity+"' AND BUSINESS_UNIT = '"+businessUnit+"' AND "
						+ "CLIENT_ID = "+clientId+" AND CLIENT_LOCATION_NO = '"+clientLocationNo+"' AND PRODUCT_ID ='"+productId+"'";
				
				boolean recordExist = new GetData().recordExist(selectString);
				if(recordExist){
					jsonRes.put("status","Already");
					System.out.println("json put if" +jsonRes);
				}else{
					Slab slabObject=setter(new Slab(),jsonObject);
					slabObject.populateNullFields();	
					new InsertData().insertSlab(slabObject);
					jsonRes.put("status","Inserted" );
					System.out.println("success");
				}
	        }catch(JSONException e)
	        {
	        	e.printStackTrace();
	        }
	
	        System.out.println("jsonRes.toString()" + jsonRes.toString());
			
			out.write(jsonRes.toString());
		
		
	}

	
}
