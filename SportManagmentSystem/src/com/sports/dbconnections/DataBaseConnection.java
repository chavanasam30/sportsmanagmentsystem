package com.sports.dbconnections;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnection {

	private static Connection con;
	Properties prop = new Properties();
	
	public static Connection getCon() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void setCon(Connection con) {
		DataBaseConnection.con = con;
	}

	public DataBaseConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		try {
			prop.load(new FileReader( new File(System.getenv("EXP_ROOT")+"/database_prop.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		con = DriverManager.getConnection
				(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		setCon(con);
	}
	
}