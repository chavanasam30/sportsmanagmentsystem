package com.experian.finance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.experian.finance.dto.DatabaseConnection;
import com.experian.finance.dto.GetData;
import com.experian.finance.work.Output;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class OutputServlet extends HttpServlet 
{
	List<Output> outputList = new ArrayList<Output>();

	private static DatabaseConnection dbConnection;
	private static Connection connection;
	private static Statement statement;
	
	private String clientId;
	private String clientName;
	private String month;
	private String year;
	
	public OutputServlet()
	{
		dbConnection = DatabaseConnection.getObject();
		connection=dbConnection.getConnection();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
  
        JSONObject jsonobject=null;
        
		String parameter=request.getParameter("dataSend");
		
		  if(null != parameter && !parameter.equalsIgnoreCase(""))
			{
			  	if (parameter.equalsIgnoreCase("clientName")) {
			  		jsonobject=	getClientName();
				}else if(parameter.equalsIgnoreCase("clientId"))
			  	{
			  		jsonobject=	getClientId();
			  	}else if (parameter.equalsIgnoreCase("month")) {
			  		jsonobject=	getMonths();
				}else if (parameter.equalsIgnoreCase("year")) {
					jsonobject= getYears();	
				} 	
			
			  	out.println(jsonobject.toString());
			}
		 
		  String submitString = request.getParameter("dataSubmit");
		  if(null != submitString && !submitString.equalsIgnoreCase(""))
		  {
			  JSONObject jsonObjectSubmit=null;
			  
			try {
					jsonObjectSubmit=new JSONObject(submitString);
					clientId=jsonObjectSubmit.get("CLIENT_ID").toString();
					clientName = jsonObjectSubmit.get("CLIENT_NAME").toString();
					month = jsonObjectSubmit.get("MONTH").toString();
					year = jsonObjectSubmit.get("YEAR").toString();
					
			
					outputList = new GetData().getOutput(clientId,clientName,month,year);
					Gson gson = new Gson();
					String jsonString= gson.toJson(outputList);
					System.out.println("jsonString" +jsonString);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
				   response.getWriter().write(jsonString);
					
		  		} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		
	}

	protected JSONObject getClientId(){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_ID) from FIN_OUTPUT";
					
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
	
	protected JSONObject getClientName(){
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_NAME) from FIN_OUTPUT";
					
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
	
	

	private JSONObject getYears() {
		JSONObject jsonobject=null;
		try {
			
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select DISTINCT(year(PROCESS_DATE)) as PROCESS_DATE from FIN_OUTPUT";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("PROCESS_DATE");
				System.out.println("PROCESS_DATE "+jsonValue);
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

	private JSONObject getMonths() {
		JSONObject jsonobject=new JSONObject();
			
			try {
				jsonobject.put("1","JAN");
				jsonobject.put("2","FEB");
				jsonobject.put("3","MAR");
				jsonobject.put("4","APR");
				jsonobject.put("5","MAY");
				jsonobject.put("6","JUN");
				jsonobject.put("7","JUL");
				jsonobject.put("8","AUG");
				jsonobject.put("9","SEP");
				jsonobject.put("10","OCT");
				jsonobject.put("11","NOV");
				jsonobject.put("12","DEC");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return jsonobject;
		
	}
	
}
