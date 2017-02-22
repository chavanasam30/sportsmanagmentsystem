package com.experian.finance;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.experian.finance.dto.DatabaseConnection;
import com.experian.finance.dto.GetData;
import com.experian.finance.main.FinanceWorking;
import com.experian.finance.work.Input;
import com.experian.finance.work.Output;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class MeterInputServlet extends HttpServlet
{
	private final String UPLOAD_DIRECTORY = "C:/EXP_ROOT";
	
	public ArrayList<String> legalEntityList;
	public ArrayList<String> businessUnit;
	public ArrayList<String> clientId;
	public ArrayList<String> clientLocation;
	public ArrayList<String> productId;
	
	public List<Output> outputList = new ArrayList<Output>();
	
	private static DatabaseConnection dbConnection;
	private static Connection connection;
	private static Statement statement;
	
	private final String STR_BLANK="";
	ArrayList<Input> inputList=new ArrayList<Input>();
	
	public MeterInputServlet()
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
		
		PrintWriter out = response.getWriter();
		
		response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		String parameter=request.getParameter("dataSend");
		
		JSONObject jsonObject=null;
		if(null != parameter)
		{
			if(parameter.equalsIgnoreCase("legalEntity")){
				jsonObject=getLegalEntity();
			}else if (parameter.equalsIgnoreCase("businessUnit")) {
				jsonObject=getBusinessUnit();
			}else if (parameter.equalsIgnoreCase("clientId")) {
				jsonObject=getClientID();
			}else if (parameter.equalsIgnoreCase("clientlocationNo")) {
				jsonObject=getClientLocationNo();
			}else if (parameter.equalsIgnoreCase("productID")) {
				jsonObject=getProductId();
			}
			
			out.println(jsonObject.toString());
		}
		
			String submitDataString =request.getParameter("dataSubmit");
			if(null != submitDataString && !submitDataString.equalsIgnoreCase(""))
			{
				
					JSONObject jsonRes = new JSONObject();
					JSONObject jsonObject2 = null;
					try {
						//jsonRes.put("status","Inserted" );
						//System.out.println("susses");
						jsonObject2 = new JSONObject(submitDataString);
						
						System.out.println("jsonObject2 : "+jsonObject2);
						FinanceWorking financeWorking=new FinanceWorking();
						Input input=new Input();
						
						String legalEntity= jsonObject2.getString("LEGAL_ENTITY");
						String businessUnit= jsonObject2.getString("BUSINESS_UNIT");
						int clientId= Integer.parseInt(jsonObject2.getString("CLIENT_ID"));
						String clientLocationNo = jsonObject2.getString("CLIENT_LOCATION_NO");
						String productId = jsonObject2.getString("PRODUCT_ID");
						
						String selectString = "select * from FIN_INPUT"
								+ " where LEGAL_ENTITY = '"+legalEntity+"' AND BUSINESS_UNIT = '"+businessUnit+"' AND "
								+ "CLIENT_ID = "+clientId+" AND CLIENT_LOCATION_NO = '"+clientLocationNo+"' AND PRODUCT_ID ='"+productId+"'";
						
						boolean recordExist = new GetData().recordExist(selectString);
						System.out.println("recordExist : "+recordExist);
						if(recordExist){
							jsonRes.put("status","Already");
							System.out.println("json put if" +jsonRes);
							 
						}else if(jsonObject2.get("register").toString().equalsIgnoreCase("Y"))
						{
							input.setLegalEntity(jsonObject2.get("LEGAL_ENTITY").toString());
							input.setBusinessUnit(jsonObject2.get("BUSINESS_UNIT").toString());
							input.setClientId(Integer.parseInt(jsonObject2.get("CLIENT_ID").toString()));
							input.setClientLocationNo(jsonObject2.get("CLIENT_LOCATION_NO").toString());
							input.setClientName(jsonObject2.get("CLIENT_NAME").toString());
							input.setNickName(jsonObject2.get("NICK_NAME").toString());
							input.setTransactionType(jsonObject2.get("TRANSACTION_TYPE").toString());
							input.setCharges(Long.parseLong(jsonObject2.get("CHARGES").toString()));
							input.setProductId(jsonObject2.get("PRODUCT_ID").toString());
							input.setQuantity(Integer.parseInt(jsonObject2.get("QUANTITY").toString()));
							input.setAccountSetupFlag(jsonObject2.get("ACCOUNT_SETUP_FLAG").toString());
							Date inputProcessDate = getDate(jsonObject2.get("PROCESS_DATE").toString());
							input.setProcessDate(inputProcessDate);
							inputList.add(input);
							//financeWorking.processAndRegisterInput(inputList);
							jsonRes.put("status","Inserted");
							System.out.println("json put if" +jsonRes);
							//out.println(jsonRes.toString());
							
						}else if (jsonObject2.get("notRegister").toString().equalsIgnoreCase("Y")) 
						{
							input.setLegalEntity(jsonObject2.get("LEGAL_ENTITY").toString());
							input.setBusinessUnit(jsonObject2.get("BUSINESS_UNIT").toString());
							input.setClientId(Integer.parseInt(jsonObject2.get("CLIENT_ID").toString()));
							input.setClientLocationNo(jsonObject2.get("CLIENT_LOCATION_NO").toString());
							input.setClientName(jsonObject2.get("CLIENT_NAME").toString());
							input.setNickName(jsonObject2.get("NICK_NAME").toString());
							input.setTransactionType(jsonObject2.get("TRANSACTION_TYPE").toString());
							input.setCharges(Long.parseLong(jsonObject2.get("CHARGES").toString()));
							input.setProductId(jsonObject2.get("PRODUCT_ID").toString());
							input.setQuantity(Integer.parseInt(jsonObject2.get("QUANTITY").toString()));
							input.setAccountSetupFlag(jsonObject2.get("ACCOUNT_SETUP_FLAG").toString());
							SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy");
							SimpleDateFormat dateFormat1=new SimpleDateFormat("dd-MMM-yy");
							Date inputProcessDate = getDate(jsonObject2.get("PROCESS_DATE").toString());
							input.setProcessDate(inputProcessDate);
							inputList.add(input);
							List<Output> outputList=financeWorking.processInput(inputList);
							
							Gson gson = new Gson();
							String jsonString= gson.toJson(outputList);
							System.out.println("jsonString Not inserted:" +jsonString);
							jsonRes.put("status","Notregister");
							jsonRes.put("output", jsonString);
							System.out.println("json put if" +jsonRes);
							//out.println(jsonRes.toString());
							
						}
						
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.write(jsonRes.toString());
				
		}
		 			
	}
	protected Date getDate(String date){
		Date dateParsed;
		try {
			//System.out.println("date"+date);
		if(date.trim().equalsIgnoreCase(""))
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
			dateParsed = sdf.parse("01-Feb-0001");
			return dateParsed;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		dateParsed = sdf.parse(date);
		//System.out.println("date1"+dateParsed);
		
		return dateParsed;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	protected JSONObject getLegalEntity(){
		
		JSONObject jsonobject=null;
		try {
			if(connection.isClosed())
				connection = dbConnection.getConnection();
			
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
			//dbConnection.close();
			
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
	protected JSONObject getBusinessUnit(){
		
		JSONObject jsonobject=null;
		try {	
			if(connection.isClosed())
				connection = dbConnection.getConnection();
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(BUSINESS_UNIT) from fin_contract";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("BUSINESS_UNIT");
				jsonobject.put(jsonValue, jsonValue);
			}
			//connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		
		
		return jsonobject;
	}
	
	protected JSONObject getClientID(){
		JSONObject jsonobject=null;
		try {
			if(connection.isClosed())
				connection = dbConnection.getConnection();
			jsonobject=new JSONObject();
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_ID) from fin_contract";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("CLIENT_ID");
				jsonobject.put(jsonValue, jsonValue);
			}
			
			
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
	protected JSONObject getClientLocationNo(){
		
		JSONObject jsonobject=null;
		try {
			if(connection.isClosed())
				connection = dbConnection.getConnection();
			jsonobject=new JSONObject();
			
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(CLIENT_LOCATION_NO) from fin_contract";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("CLIENT_LOCATION_NO");
				jsonobject.put(jsonValue, jsonValue);
			}
			
			
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
	
	protected JSONObject getProductId()
	{
		JSONObject jsonObject=null;		
		try {
			if(connection.isClosed())
				connection = dbConnection.getConnection();
			jsonObject=new JSONObject();
			
			statement = connection.createStatement();
			ResultSet resultSetSlab = null;
			String selectSlab="select distinct(PRODUCT_ID) from fin_contract";
					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString("PRODUCT_ID");
				jsonObject.put(jsonValue, jsonValue);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return jsonObject;
		
	}
	
}
