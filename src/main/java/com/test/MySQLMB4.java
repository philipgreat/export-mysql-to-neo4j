package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLMB4 {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
        Class.forName(driverName);
    
        // Create a connection to the database
        String serverName = "localhost";
        String mydatabase = "bbt";
        String url = "jdbc:mysql://" + serverName +  ":3306/" + mydatabase+"?useSSL=false"; // a JDBC url
        String username = "root";
        String password = "0254891276";
        Connection connection = DriverManager.getConnection(url, username, password);
     
		return connection;
		
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Statement statment = conn.createStatement();
		statment.executeUpdate("insert into uu values('😋')");
		
	}

}
