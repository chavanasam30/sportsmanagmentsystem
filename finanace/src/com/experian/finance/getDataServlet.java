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
import com.google.gson.Gson;

/**
 * Servlet implementation class getDataServlet
 */

public class getDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String STR_BLANK = "";
	private static DatabaseConnection dbConnection;
	private static Connection connection;
	private static Statement statement;
	
	public String legalEntity;
	public String businessunit;
	public String clientId;
	public String clientName;
	public String clientLocationNo;
	public String productId;
	
	public getDataServlet()
	{
		dbConnection = DatabaseConnection.getObject();
		connection=dbConnection.getConnection();
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
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
			System.out.println("legalEntity "+jsonobject);
		}else if(parameter.equals("businessUnit")){
			String legalEntity=request.getParameter("legalEntity");	
			jsonobject=	getBusinessUnit(legalEntity);
		     System.out.println(jsonobject);
		}else if(parameter.equalsIgnoreCase("clientName")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			jsonobject = getClientName(legalEntity,businessunit);
			System.out.println(jsonobject);
		}else if(parameter.equals("clientId")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			String clientName=request.getParameter("clientName");
			jsonobject=	getClientID(legalEntity,businessunit,clientName);
			System.out.println("businessUnit :"+jsonobject);
		}else if(parameter.equals("clientlocationNo")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			String clientId=request.getParameter("clientId");
			String clientName = request.getParameter("clientName");
			jsonobject=	getClientLocationNo(legalEntity,businessunit,clientId,clientName);
			System.out.println("clientlocationNo :"+jsonobject);
		}else if(parameter.equals("productID")){
			String legalEntity=request.getParameter("legalEntity");
			String businessunit=request.getParameter("businessUnit");
			String clientId=request.getParameter("clientId");
			String clientName = request.getParameter("clientName");
			String clientLocationNo=request.getParameter("clientlocationNo");
			jsonobject=	getProductID(legalEntity,businessunit,clientId,clientName,clientLocationNo);
			System.out.println("productID :"+jsonobject);
		}
		
		out.println(jsonobject.toString());
	}
		
		String submitDataString=request.getParameter("dataSubmit");
		if(null != submitDataString && !submitDataString.equalsIgnoreCase("")){
			JSONObject jsonObjectSubmit=null;
			
			System.out.println("submitDataString "+submitDataString);
				try {
						jsonObjectSubmit=new JSONObject(submitDataString);
						legalEntity =jsonObjectSubmit.get("LEGAL_ENTITY").toString();
						businessunit=jsonObjectSubmit.get("BUSINESS_UNIT").toString();
						clientId=jsonObjectSubmit.get("CLIENT_ID").toString();
						clientName = jsonObjectSubmit.get("CLIENT_NAME").toString();
						clientLocationNo=jsonObjectSubmit.get("CLIENT_LOCATION_NO").toString();
						productId=jsonObjectSubmit.get("PRODUCT_ID").toString();
						
						//ArrayList<Slab> searchSlab = new GetData().getSearchSlab(legalEntity, businessunit, clientId, clientLocationNo, productId);
						
						JSONObject jsonRes=new JSONObject();
						
						 // ArrayList<Slab> slab=new ArrayList<Slab>();
						 // slab=new GetData().getSearchSlab(legalEntity, businessunit, clientId, clientLocationNo, productId);
						 JSONArray jsonArray = null;
						
						
						//ResultSet resultSet= new GetData().getSearchContract(legalEntity, businessunit, clientId, clientLocationNo, productId);
						 List<Map<String, Object>> resultSetList= new GetData().getSearchContract(legalEntity, businessunit, clientId, clientLocationNo, productId, clientName);
						 System.out.println("resultsetList :" + resultSetList);
						/*	try {
								jsonArray = ResultSetConverter.convert(resultSet);
							} catch (SQLException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
							
						  response.setContentType("application/json");
						  System.out.println(jsonArray);
						
						out.print(jsonArray);*/
						 
						 Gson gson = new Gson();
						  String jsonString= gson.toJson(resultSetList);
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
		
		if(null != saveDataString && !saveDataString.equalsIgnoreCase(STR_BLANK))
		{
			JSONObject jsonRes = new JSONObject();
			Map<String,String> mapContract = new HashMap<String, String>();
							
			ArrayList<String> list = new ArrayList<String>(); 
					try {
					JSONArray array = new JSONArray(saveDataString);
					int len=array.length();
					for (int i=0;i<len;i++){ 
					    list.add(array.get(i).toString());
					} 
					if(len>6)
					{
						JSONObject listJsonObject = null;
						JSONArray jsonArray = new JSONArray();
						
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
								mapContract.put(jsonString, listJsonObject.getString(jsonString));
							}
						}
						
						System.out.println("save Map:" + mapContract);
						InsertData insertData = new InsertData();
						boolean flag =insertData.updateContract(mapContract);
						
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
	
	
	protected JSONObject getLegalEntity(){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(LEGAL_ENTITY) from fin_contract";
					
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
		}	
		return jsonobject;
	}
	
	protected JSONObject getBusinessUnit(String legalEntity){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(BUSINESS_UNIT) from fin_contract where LEGAL_ENTITY= '"+legalEntity+"'";
					
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
		}
		return jsonobject;
	}

	protected JSONObject getClientID(String legalEntity,String businessUnit,String clientName){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_ID) from fin_contract where BUSINESS_UNIT= '"+businessUnit+"' and LEGAL_ENTITY= '"+legalEntity+"'and CLIENT_NAME='"+clientName+"'";
			
			System.out.println("select Slab : "+selectSlab);
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
		}
		return jsonobject;
	}
	
	private JSONObject getClientName(String legalEntity, String businessUnit) {
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_NAME) from fin_contract where BUSINESS_UNIT= '"+businessUnit+"' and LEGAL_ENTITY ='"+legalEntity +"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("CLIENT_NAME");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobject;
	}

	protected JSONObject getClientLocationNo(String legalEntity,String businessUnit,String clientId, String clientName){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_LOCATION_NO) from fin_contract where BUSINESS_UNIT= '"+businessUnit+"' and LEGAL_ENTITY ='"+legalEntity 
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
		}
		return jsonobject;
	}

	private JSONObject getProductID(String legalEntity, String businessunit,String clientId,String clientName, String clientLocationNo) {
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(PRODUCT_ID) from fin_contract where BUSINESS_UNIT= '"+businessunit+"' and LEGAL_ENTITY='"+legalEntity
					+ "' and CLIENT_ID= "+clientId+" and CLIENT_NAME='"+clientName+"' and CLIENT_LOCATION_NO= '"+clientLocationNo+"'";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("PRODUCT_ID");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobject;
	}

	
	

}
