package com.sports.dbconnections;

import java.sql.*;

public class DataBaseConnection {

	private static Connection con;
	
	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		DataBaseConnection.con = con;
	}

	public DataBaseConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");  	
		con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/Sport_Database", "root", "sampada30");
		setCon(con);
	}
	
}
