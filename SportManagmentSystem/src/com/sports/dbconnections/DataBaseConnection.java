package com.sports.dbconnections;

import java.sql.*;

public class DataBaseConnection {

	private static Connection con;
	
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
		con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/Sport_Database", "root", "root");
		setCon(con);
	}
	
}
