package com.experian.finance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;














import com.experian.finance.dto.DatabaseConnection;
import com.experian.finance.dto.GetData;
import com.experian.finance.dto.InsertData;
import com.experian.finance.master.Contract;
import com.experian.finance.master.Slab;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class GetSlabDataServlet extends HttpServlet{

	private static final String STR_BLANK = "";
	private static final String STR_NOTSELECTED = "NotSelected";
	private static DatabaseConnection dbConnection;
	private static Connection connection;
	private static Statement statement;
	public String legalEntity;
	public String businessunit;
	public String clientId;
	public String clientName;
	public String clientLocationNo;
	public String productId;
	
	
	
	private List<String> resultValue = new ArrayList<String>();
	
	public GetSlabDataServlet()
	{
		dbConnection = DatabaseConnection.getObject();
		connection=dbConnection.getConnection();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new GetSlabDataServlet().doPost(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		String parameter=request.getParameter("dataSend");
		JSONObject jsonobject=null;
		if(null != parameter && !parameter.equalsIgnoreCase("")){
		if(parameter.equals("legalEntity")){
			jsonobject=	getLegalEntity();
		}else if(parameter.equals("businessUnit")){
			String legalEntity=request.getParameter("legalEntity");	
			jsonobject=	getBusinessUnit(legalEntity);
		}else if(parameter.equals("clientName")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			jsonobject=	getClientName(legalEntity,businessunit);
		}else if(parameter.equals("clientId")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			String clientName= request.getParameter("clientName");
			jsonobject=	getClientID(legalEntity,businessunit,clientName);
		}else if(parameter.equals("clientlocationNo")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			String clientId=request.getParameter("clientId");
			String clientName = request.getParameter("clientName");
			jsonobject=	getClientLocationNo(legalEntity,businessunit,clientId,clientName);
		}else if(parameter.equals("productID")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			String clientId=request.getParameter("clientId");
			String clientName = request.getParameter("clientName");
			String clientLocationNo=request.getParameter("clientlocationNo");
			jsonobject=	getProductID(legalEntity,businessunit,clientId,clientName,clientLocationNo);
		}
		
		out.println(jsonobject.toString());
	}
		
		String submitDataString=request.getParameter("dataSubmit");
		if(null != submitDataString && !submitDataString.equalsIgnoreCase("")){
			
			List<Map<String, Object>> resultsetList = null ;
			JSONObject jsonObjectSubmit=null;
			JSONObject jsonRes=new JSONObject();
			
			System.out.println("submitDataString "+submitDataString);
			
			try {
						jsonObjectSubmit=new JSONObject(submitDataString);
						
						legalEntity =jsonObjectSubmit.get("LEGAL_ENTITY").toString();
						businessunit=jsonObjectSubmit.get("BUSINESS_UNIT").toString();
						clientId=jsonObjectSubmit.get("CLIENT_ID").toString();
						clientName =jsonObjectSubmit.get("CLIENT_NAME").toString();
						clientLocationNo=jsonObjectSubmit.get("CLIENT_LOCATION_NO").toString();
						productId=jsonObjectSubmit.get("PRODUCT_ID").toString();
						
						  resultsetList= new GetData().getSearchSlab(legalEntity, businessunit, clientId, clientName, clientLocationNo, productId);
						
						  Gson gson = new Gson();
						  String jsonString= gson.toJson(resultsetList);
						  System.out.println("jsonString" +jsonString);
						  response.setContentType("application/json");
						  response.setCharacterEncoding("UTF-8");
						//  response.getWriter().write("{\"slab\" :" +jsonString +"}");
						  response.getWriter().write(jsonString);
				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
		}
			String saveDataString = request.getParameter("dataEdit");
			Map<String,String> mapSlab = new HashMap<String, String>();
			if(null != saveDataString && !saveDataString.equalsIgnoreCase(STR_BLANK))
			{
				JSONObject jsonRes = new JSONObject();
												
				ArrayList<String> list = new ArrayList<String>(); 
				
						try {
						JSONArray jsonArray = new JSONArray(saveDataString);
						int len=jsonArray.length();
						//System.out.println("len "+len);
						for (int i=0;i<len;i++){ 
						    list.add(jsonArray.get(i).toString());
						}
						
							JSONObject listJsonObject = null;
							
							@SuppressWarnings("unused")
							Iterator<String> listIterator = list.iterator();
							
							for (@SuppressWarnings("rawtypes")
							Iterator iterator = list.iterator(); iterator.hasNext();) 
							{
								String string = (String) iterator.next();
								
								listJsonObject = new JSONObject(string);
								@SuppressWarnings("unchecked")
								Iterator<String> jsonIterator = listJsonObject.keys();
								
								while (jsonIterator.hasNext()) {
									String jsonString = (String) jsonIterator.next();
									if(!listJsonObject.get(jsonString).toString().contains("null")){
									//System.out.println(jsonString+" : " +listJsonObject.get(jsonString));
									mapSlab.put(jsonString, (String) listJsonObject.get(jsonString));
								}else{
									mapSlab.clear();
								}
								 
							  }
							
							}
							System.out.println("############### Map #######################");
							System.out.println(mapSlab);
							if(mapSlab.size()>0)
							{
								InsertData insertData = new InsertData();
								boolean flag =insertData.updateSlab(mapSlab);
								
								if(flag){
									jsonRes.put("status","success");
								}
								else{
									jsonRes.put("status","error" );
								}
							}
							
					} catch (JSONException e) {
						e.printStackTrace();
					}
						out.println(jsonRes.toString()); 
					    out.close();
					//System.out.println("list" +list);
					//System.out.println("SaveString "+ saveDataString);
			}
	
	}
	
	private JSONObject getProductID(String legalEntity, String businessunit,
			String clientId,String clientName, String clientLocationNo) {
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(PRODUCT_ID) from fin_contract_slab where BUSINESS_UNIT= '"+businessunit+"' and LEGAL_ENTITY='"+legalEntity
					+ "' and CLIENT_ID= "+clientId+" and CLIENT_LOCATION_NO= '"+clientLocationNo+"' and CLIENT_NAME='"+clientName+"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("PRODUCT_ID");
				productId=jsonValue;
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		
		return jsonobject;
	}

	protected JSONObject getBusinessUnit(String legalEntity){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(BUSINESS_UNIT) from fin_contract_slab where LEGAL_ENTITY= '"+legalEntity+"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("BUSINESS_UNIT");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		
		return jsonobject;
	}
	protected JSONObject getClientID(String legalEntity,String businessUnit, String clientName){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_ID) from fin_contract where BUSINESS_UNIT= '"+businessUnit+"' and LEGAL_ENTITY= '"+legalEntity+"'and CLIENT_NAME='"+clientName+"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("CLIENT_ID");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		
		return jsonobject;
	}
	
	private JSONObject getClientName(String legalEntity, String businessunit) {
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_NAME) from fin_contract where BUSINESS_UNIT= '"+businessunit+"' and LEGAL_ENTITY ='"+legalEntity +"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("CLIENT_NAME");
				clientName=jsonValue;
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		
		return jsonobject;
		
	}
	protected JSONObject getClientLocationNo(String legalEntity,String businessUnit,String clientId, String clientName){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_LOCATION_NO) from fin_contract_slab where BUSINESS_UNIT= '"+businessUnit+"' and LEGAL_ENTITY ='"+legalEntity 
					+ "'and CLIENT_ID="+clientId+" and CLIENT_NAME='"+clientName+"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("CLIENT_LOCATION_NO");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		
		return jsonobject;
	}
	protected JSONObject getLegalEntity(){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(LEGAL_ENTITY) from fin_contract_slab";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("LEGAL_ENTITY");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		return jsonobject;
	}
	public List<String> getResultValue() {
		return resultValue;
	}
	public void setResultValue(List<String> searchSlab) {
		this.resultValue = searchSlab;
	}
	
	
	
	

}
