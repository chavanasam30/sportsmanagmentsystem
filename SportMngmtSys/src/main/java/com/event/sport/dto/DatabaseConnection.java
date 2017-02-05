package com.event.sport.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
	private static Connection connection;
	private static DatabaseConnection databaseConnect = null;
	
	private DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_Database", "root", "root");
			connection.setAutoCommit(false);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DatabaseConnection getObject() {
		if (databaseConnect == null) {
			databaseConnect = new DatabaseConnection();
		}
		return databaseConnect;
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
