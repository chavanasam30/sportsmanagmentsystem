package com.sport.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mysql.jdbc.ResultSetMetaData;
import com.sports.dbconnections.DataBaseConnection;

public class ParticipantServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gameDropDownOption;
	private static DataBaseConnection dbConnection;
	private static Connection connection;
	private static PreparedStatement statement;
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-M-yyyy");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	private String partID = "";
	String userName = "";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			dbConnection = new DataBaseConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String gameStringJson = "";
		JSONObject jsonobject=null;
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		
       
		String gameData = request.getParameter("dataGame");
		
		 String parameter= request.getParameter("dataSelected"); 
		 
		if(null != gameData && !gameData.equalsIgnoreCase("")){
			try {
				jsonobject = new JSONObject(gameData);
			
				
				String ageJson = jsonobject.get("ageText").toString();
				String age = getAgeGroup(ageJson);
				String gender = jsonobject.get("selectGender").toString();
				String fname = jsonobject.get("txtFName").toString();
				String mname = jsonobject.get("txtMName").toString();
				String lname = jsonobject.get("txtLName").toString();
				String dateOfBirth = jsonobject.get("txtDateOfBirth").toString();
				String email = jsonobject.get("txtEmailId").toString();
				String phone = jsonobject.get("txtPhone").toString();
				String emerPhone = jsonobject.get("txtEmerPhone").toString();
				String add1 = jsonobject.get("txtAdd1").toString();
				String add2 = jsonobject.get("txtAdd2").toString();
				String city = jsonobject.get("txtCity").toString();
				String state = jsonobject.get("txtState").toString();
				String pincode = jsonobject.get("txtPincode").toString();
				String schoolName = jsonobject.get("txtSch").toString();
				String schAdd1 = jsonobject.get("txtSchAdd1").toString();
				String schAdd2 = jsonobject.get("txtSchAdd2").toString();
				String schCity = jsonobject.get("txtSchCity").toString();
				String schstate = jsonobject.get("txtSchState").toString();
				String schPincode = jsonobject.get("txtSchPincode").toString();
				userName = "admin";
				
				
				insertIntoParticipantTables(fname,mname,lname,dateOfBirth,email,phone,emerPhone,add1,add2,city,state,pincode,
						schoolName,schAdd1,schAdd2,schCity,
						schstate,schPincode,ageJson,gender,userName);
				
				partID = getPartID();
				jsonobject = getGameOptions(age,gender,jsonobject);
				
				out.println(jsonobject.toString()); 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(null != parameter && !parameter.equalsIgnoreCase("")){
				
				JSONObject jsonRes = new JSONObject();
				
				ArrayList<String> list = new ArrayList<String>(); 
				Set<String> gameIdList = new HashSet<String>(); 
						try {
						JSONArray jsonArray = new JSONArray(parameter);
						int len=jsonArray.length();
						//System.out.println("len "+len);
						for (int i=0;i<len;i++){ 
						    list.add(jsonArray.get(i).toString());
						}
						
							JSONObject listJsonObject = null;
							
							Iterator<String> iterator = list.iterator();
							
							
							while(iterator.hasNext()) 
							{
								String string = (String) iterator.next();
								
								listJsonObject = new JSONObject(string);
								@SuppressWarnings("unchecked")
								Iterator<String> jsonIterator = listJsonObject.keys();
								
								while (jsonIterator.hasNext()) {
									String jsonString = (String) jsonIterator.next();
									System.out.println("************ "+jsonString);
									if(!listJsonObject.get(jsonString).toString().contains("null")){
									gameIdList.add(jsonString);
								}else{
									gameIdList.clear();
								}
								 
							  }
							
							}
							System.out.println("############### Map #######################");
							System.out.println(gameIdList);
							if(gameIdList.size()>0)
							{
								insertIntoPartiGameTable(gameIdList,partID,userName);
							}
							jsonRes.put("partID", partID);
							out.println(jsonRes.toString()); 
							out.close();
					} catch (JSONException e) {
						e.printStackTrace();
					}
						
				
			}
			
		
		
	}

	
	private String getPartID(){
		String partID = "";
		
		connection = dbConnection.getCon();
		
		try {
			PreparedStatement stmt2 = connection.prepareStatement("SELECT PART_ID FROM PARTICIPANT"
					+ " ORDER BY PART_ID DESC "
					+ "LIMIT 1");
		ResultSet resultSet = stmt2.executeQuery();
		
		while (resultSet.next()) {
				partID = resultSet.getString("PART_ID");
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return partID;
		
	}

	private void insertIntoParticipantTables(String fname, String mname, String lname, String dateOfBirth, String email,
			String phone, String emerPhone, String add1, String add2, String city, String state, String pincode,
			String schoolName, String schAdd1, String schAdd2, String schCity, String schstate, String schPincode, String age,
			String gender, String userName) {
		
		Date date = null;
		
		try {
			date = dateFormat1.parse(dateOfBirth);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String dateOfBirth1 = dateFormat2.format(date);
		connection = dbConnection.getCon();
	
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into PARTICIPANT(FNAME,MNAME,LNAME,"
					+ "DOB,AGE,SCHOOL,ADDRESS_LINE1,ADDRESS_LINE2,STATE,CITY,PINCODE,SCHOOL_ADDRESS_LINE1,"
					+ "SCHOOL_ADDRESS_LINE2,SCHOOL_STATE,SCHOOL_CITY,SCHOOL_PINCODE,GENDER,PHONE,EMER_PHONE,EMAIL_ID,INSERT_USER_NAME) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setString(1, fname);
			stmt.setString(2, mname);
			stmt.setString(3, lname);
			stmt.setString(4, dateOfBirth1);
			stmt.setString(5, age);
			stmt.setString(6,schoolName);
			stmt.setString(7,add1);
			stmt.setString(8,add2);
			stmt.setString(9,state);
			stmt.setString(10,city);
			stmt.setString(11,pincode);
			stmt.setString(12,schAdd1);
			stmt.setString(13,schAdd2);
			stmt.setString(14,schstate);
			stmt.setString(15,schCity);
			stmt.setString(16,schPincode);
			stmt.setString(17,gender);
			stmt.setString(18,phone);
			stmt.setString(19,emerPhone);
			stmt.setString(20,email);
			stmt.setString(21,userName);
			
			stmt.executeUpdate();
			
			connection.commit();
			
			//modelMap.addAttribute("message", fname+" "+mname+" "+lname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("static-access")
	public void insertIntoPartiGameTable(Set<String> gameIdList, String partID,String userName){
		
		connection = dbConnection.getCon();
		PreparedStatement stmt = null;
		try {
			String query = "insert into PARTI_GAME(PART_ID,GAME_ID,INSERT_USER_NAME) "
					+ "values(?,?,?)";
			stmt = connection.prepareStatement(query);
			
			for(String gameId : gameIdList){
				stmt.setString(1, partID);
				stmt.setInt(2, NumberUtils.toInt(gameId, 0));
				stmt.setString(3, userName);
				stmt.addBatch();	
			}
			stmt.executeBatch();
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private String getAgeGroup(String parameter) {
		int age = Integer.parseInt(parameter);
		String ageGrp = "";
		if(age <= 10){
			ageGrp = "U10,U12,U14,U16,U17,U18,U19,U20,U21";
		}else if(age <= 12 && age > 10){
			ageGrp = "U12,U14,U16,U17,U18,U19,U20,U21";
		}else if(age <= 14 && age > 12){
			ageGrp = "U14,U16,U17,U18,U19,U20,U21";
		}else if(age <= 16 && age > 14){
			ageGrp = "U16,U17,U18,U19,U20,U21";
		}else if(age <= 17 && age > 16){
			ageGrp = "U17,U18,U19,U20,U21";
		}else if(age <=18 && age > 17){
			ageGrp = "U18,U19,U20,U21";
		}else if(age <=19 && age > 18){
			ageGrp = "U19,U20,U21";
		}else if(age <=20 && age > 19){
			ageGrp = "U20,U21";
		}else if(age <=21 && age > 20){
			ageGrp = "U21";
		}
		return ageGrp;
	}

	@SuppressWarnings("static-access")
	private JSONObject getGameOptions(String age, String gender,JSONObject jsonObject) {
		
		try {
			Map<String, Object> discipMap = new HashMap<String,Object>();
			Map<String,String> ageEventMap = null;
			
			jsonObject=new JSONObject();
			connection = dbConnection.getCon();
			
			String [] tokenAge = age.split(",");
			
			ResultSet resultSet = null;
			int count = 1; 
			String selectQuery="select GAME_ID, DISCIPLINE, CATEGORY, EVENT from GAME where CATEGORY = ? AND AGE_GRP IN (?";
			
			for(int i = 0; i< tokenAge.length-1 ;i++){
				selectQuery = selectQuery +",?";
			}
			selectQuery = selectQuery + ")";
			
			statement = connection.prepareStatement(selectQuery);
			statement.setString(count++, gender);
			
			for(int i = 0 ; i< tokenAge.length ;i++){
				statement.setString(count++, tokenAge[i]);
			}
			
			resultSet = statement.executeQuery();
			
			Map<String, Map<String,String>> disciplineGameMap = new HashMap<String,Map<String,String>>();
			Map<String, String> gameEventMap = new HashMap<String,String>();
			
 			while(resultSet.next()){
				
				String discipline =resultSet.getString("DISCIPLINE");
				String gameID = resultSet.getString("GAME_ID");
				String event = resultSet.getString("EVENT");
				if(null != disciplineGameMap.get(discipline)){
					gameEventMap = disciplineGameMap.get(discipline);
					gameEventMap.put(gameID,event);
				}else{
					gameEventMap = new HashMap<>();
					gameEventMap.put(gameID,event);
				}
				disciplineGameMap.put(discipline, gameEventMap);	
			}
 			
 			jsonObject.put("DISCIPLINE", new JSONObject(disciplineGameMap));
 			
			//connection.close();
			
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
