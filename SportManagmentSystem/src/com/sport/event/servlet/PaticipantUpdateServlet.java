package com.sport.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sports.dbconnections.DataBaseConnection;

public class PaticipantUpdateServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DataBaseConnection dbConnection;
	private static Connection connection;
	private static PreparedStatement statement;
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateFormat3 = new SimpleDateFormat("MMM dd yyyy");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			dbConnection = new DataBaseConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		
        String searchData = request.getParameter("dataSearch");
        String saveData = request.getParameter("saveData");
        
		if(null != searchData && !searchData.equalsIgnoreCase("")){
				try {
					jsonobject = new JSONObject(searchData);
					String searchID = jsonobject.get("searchID").toString();
					String searchFName = jsonobject.get("searchFName").toString();
					String searchMName = jsonobject.get("searchMName").toString();
					String searchLName = jsonobject.get("searchLName").toString();
					String searchPhone = jsonobject.get("searchPhone").toString();		
					
					List<Map<String, Object>> participantList = getSearchPaticipant(searchID,searchFName,searchMName,searchLName,searchPhone);
					
					Gson gson = new Gson();
					
					  String jsonString= gson.toJson(participantList);
					  System.out.println("jsonString" +jsonString);
					  response.setContentType("application/json");
					  response.setCharacterEncoding("UTF-8");
					  response.getWriter().write(jsonString);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		
		if(null != saveData && !saveData.equalsIgnoreCase("")){
			Map<String,String> saveParticipantMap = new HashMap<String, String>();
			if(null != saveData && !saveData.equalsIgnoreCase(""))
			{
				JSONObject jsonRes = new JSONObject();
												
				ArrayList<String> list = new ArrayList<String>(); 
				
						try {
						JSONArray jsonArray = new JSONArray(saveData);
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
									
									System.out.println(jsonString+" : " +listJsonObject.get(jsonString));
										saveParticipantMap.put(jsonString, (String) listJsonObject.get(jsonString));
								 
							  }
							
							}
							System.out.println("############### Map #######################");
							System.out.println(saveParticipantMap);
							if(saveParticipantMap.size()>0)
							{
								boolean flag =updateParticipant(saveParticipantMap);
								
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
		
		
		
		
		
	}

	private boolean updateParticipant(Map<String, String> saveParticipantMap) {
		boolean flag = false;
		PreparedStatement preparedStatement = null;
		String updateSlabStr = "UPDATE PARTICIPANT SET FNAME=?,MNAME=?,LNAME=?"
				+ ",DOB=?,AGE=?,SCHOOL=?,ADDRESS_LINE1=?,ADDRESS_LINE2=?,STATE=?,CITY=?,PINCODE=?,SCHOOL_ADDRESS_LINE1=?,"
				+ "SCHOOL_ADDRESS_LINE2=?,SCHOOL_STATE=?,SCHOOL_CITY=?,SCHOOL_PINCODE=?,GENDER=?,PHONE=?,EMER_PHONE=?,EMAIL_ID=?,"
				+ "UPDATE_USER_NAME=?"
				+ "WHERE PART_ID=?";
		
		try {
				connection = dbConnection.getCon();
			preparedStatement = connection.prepareStatement(updateSlabStr);
			
			for(Entry<String, String> entry : saveParticipantMap.entrySet())
			{ 
				if (entry.getKey().equalsIgnoreCase("FNAME")) {
					preparedStatement.setString(1,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("MNAME")) {
					preparedStatement.setString(2, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("LNAME")) {
					preparedStatement.setString(3, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");;
				}else if (entry.getKey().equalsIgnoreCase("DOB")) {
					String dateStr = entry.getValue().replace(",", "");
					Date date = null;
					
					try {
						date = dateFormat1.parse(dateStr);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						try {
							String dateStr1 = entry.getValue().replace(",", "");
							date = dateFormat3.parse(dateStr1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					String dateOfBirth1 = dateFormat2.format(date);
					preparedStatement.setString(4, dateOfBirth1);
				}else if (entry.getKey().equalsIgnoreCase("AGE")) {
					preparedStatement.setFloat(5,NumberUtils.toFloat(!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "0",0));;
				}else if(entry.getKey().equalsIgnoreCase("SCHOOL")){
					preparedStatement.setString(6,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if(entry.getKey().equalsIgnoreCase("ADDRESS_LINE1")){
					preparedStatement.setString(7,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("ADDRESS_LINE2")) {
					preparedStatement.setString(8,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("STATE")) {
					preparedStatement.setString(9,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("CITY")) {
					preparedStatement.setString(10,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("PINCODE")) {
					preparedStatement.setInt(11,NumberUtils.toInt(!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "0",0));
				}else if (entry.getKey().equalsIgnoreCase("SCHOOL_ADDRESS_LINE1")) {
					preparedStatement.setString(12,!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("SCHOOL_ADDRESS_LINE2")) {
					preparedStatement.setString(13, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("SCHOOL_STATE")) {
					preparedStatement.setString(14, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("SCHOOL_CITY")) {
					preparedStatement.setString(15, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("SCHOOL_PINCODE")) {
					preparedStatement.setString(16, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "0");
				}else if (entry.getKey().equalsIgnoreCase("GENDER")) {
					preparedStatement.setString(17, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("PHONE")) {
					preparedStatement.setInt(18, NumberUtils.toInt(!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "0",0));
				}else if (entry.getKey().equalsIgnoreCase("EMER_PHONE")) {
					preparedStatement.setInt(19, NumberUtils.toInt(!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "0",0));
				}else if (entry.getKey().equalsIgnoreCase("EMAIL_ID")) {
					preparedStatement.setString(20, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("UPDATE_USER_NAME")) {
					preparedStatement.setString(21, !entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "");
				}else if (entry.getKey().equalsIgnoreCase("PART_ID")) {
					preparedStatement.setInt(22, NumberUtils.toInt(!entry.getValue().equalsIgnoreCase("undefined") ? entry.getValue() : "0",0));
				}
			}
			
			System.out.println("Preparestatement : "+preparedStatement.toString());
			int rowUpdate =preparedStatement.executeUpdate();
			connection.commit();
			if(rowUpdate == 0)
			{
				flag =false;
			}else {
				flag = true;
				
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

	private List<Map<String, Object>> getSearchPaticipant(String searchID, String searchFName, String searchMName, String searchLName ,String searchPhone) {
		
		String whereClause = "";
		
		ResultSet resultSet = null;
		List<Map<String, Object>> participantList = new ArrayList<Map<String,Object>>();
		
		if(!searchID.equalsIgnoreCase("")){
			whereClause += (whereClause == "") ? " WHERE " + " PART_ID = "+NumberUtils.toInt(searchID, 0) : " AND "+" PART_ID = "+NumberUtils.toInt(searchID, 0);
		}
		if(!searchFName.equalsIgnoreCase("")){
			whereClause += (whereClause == "") ? " WHERE " + " FNAME = '"+searchFName+"'" : " AND "+" FNAME = '"+searchFName+"'";
		}
		if(!searchMName.equalsIgnoreCase("")){
			whereClause += (whereClause == "") ? " WHERE " + " MNAME = '"+searchMName+"'" : " AND "+" MNAME = '"+searchMName+"'";
		}
		if(!searchLName.equalsIgnoreCase("")){
			whereClause += (whereClause == "") ? " WHERE " + " LNAME = '"+searchLName+"'" : " AND "+" LNAME = '"+searchLName+"'";
		}
		
		String selectQuery = "select PART_ID,FNAME,MNAME,LNAME,"
					+ "DOB,AGE,SCHOOL,ADDRESS_LINE1,ADDRESS_LINE2,STATE,CITY,PINCODE,SCHOOL_ADDRESS_LINE1,"
					+ "SCHOOL_ADDRESS_LINE2,SCHOOL_STATE,SCHOOL_CITY,SCHOOL_PINCODE,GENDER,PHONE,"
					+ "EMER_PHONE,EMAIL_ID,INSERT_USER_NAME, UPDATE_USER_NAME,INSERT_DATE_TIME,UPDATE_DATE_TIME from PARTICIPANT"+whereClause;
		
		try {
				connection = dbConnection.getCon();
			
			PreparedStatement statement= connection.prepareStatement(selectQuery);
					
			resultSet= statement.executeQuery();
			System.out.println(resultSet);
						
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();	
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				      int numColumns = resultSetMetaData.getColumnCount();
				      
				      for (int i=1; i<numColumns+1; i++) {
				        String column_name = resultSetMetaData.getColumnName(i);

				        if(resultSetMetaData.getColumnType(i)==java.sql.Types.ARRAY){
				        	map.put(column_name, resultSet.getArray(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BIGINT){
				        	map.put(column_name, resultSet.getLong(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BOOLEAN){
				        	map.put(column_name, resultSet.getBoolean(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BLOB){
				        	map.put(column_name, resultSet.getBlob(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DOUBLE){
				        	map.put(column_name, resultSet.getDouble(column_name)); 
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.FLOAT){
				        	map.put(column_name, resultSet.getFloat(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.INTEGER){
				        	map.put(column_name, resultSet.getInt(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.NVARCHAR){
				        	map.put(column_name, resultSet.getNString(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.VARCHAR){
				        	map.put(column_name, resultSet.getString(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.TINYINT){
				        	map.put(column_name, resultSet.getInt(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.SMALLINT){
				        	map.put(column_name, resultSet.getInt(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DATE){
				        	map.put(column_name, resultSet.getDate(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.TIMESTAMP){
				        	map.put(column_name, resultSet.getTimestamp(column_name));   
				        }
				        else{
				        	map.put(column_name, resultSet.getObject(column_name));
				        }
				      }
				      participantList.add(map);
				      System.out.println(map.toString());
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participantList;
		
	}
	

}
