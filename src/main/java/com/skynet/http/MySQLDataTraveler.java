package com.skynet.http;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close" >
	
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url"	value="jdbc:mysql://localhost:3306/b2b2c?characterEncoding=utf8&amp;autoReconnect=true" />
		<property name="username" value="root" />
		<property name="password" value="0254891276" />
		<property name="removeAbandoned" value="true"/>
		<property name="initialSize" value="20" />
		<property name="maxActive" value="30" />

	</bean>
 * 
 * */
public class MySQLDataTraveler {

	public MySQLDataTraveler() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<String> getAllTables(Connection connection) throws SQLException{
		
		DatabaseMetaData dbmd = connection.getMetaData();
	    
        // Specify the type of object; in this case we want tables
        String[] types = {"TABLE"};
        ResultSet resultSet = dbmd.getTables(null, null, "%", types);
        
        List<String> tableNames = new ArrayList<String>();
        // Get the table names
        while (resultSet.next()) {
            // Get the table name
            String tableName = resultSet.getString(3);
            tableNames.add(tableName);
            // Get the table's catalog and schema names (if any)
            //String tableCatalog = resultSet.getString(1);
            //String tableSchema = resultSet.getString(2);
        }
        
        
        return tableNames;
        
        
		
		
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		
		String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
        Class.forName(driverName);
    
        // Create a connection to the database
        String serverName = "localhost";
        String mydatabase = "b2b2c";
        String url = "jdbc:mysql://" + serverName +  ":3306/" + mydatabase; // a JDBC url
        String username = "root";
        String password = "0254891276";
        Connection connection = DriverManager.getConnection(url, username, password);
     
		return connection;
		
	}
	
	
	public void list(Connection conn, List<String>tableNames) throws SQLException, ClassNotFoundException{
		
		Statement stat = conn.createStatement();
		stat.setFetchSize(1000);
		for(String tableName:tableNames ){
			
			ResultSet rs =  stat.executeQuery("select * from " + tableName);
			
			while(rs.next()){
				
				
				handleRecord(tableName,rs);
				
			}
			
		}
		
	}
	
	public void handleRecord(String tableName,ResultSet rs) throws SQLException{
		
		System.out.println(rs.getString("id"));
	}
	
	
	
	
	public  static void main(String []args) throws ClassNotFoundException, SQLException{
		
		MySQLDataTraveler mt = new MySQLDataTraveler();
		Connection conn  = mt.getConnection();
		List<String>tableNames = mt.getAllTables(conn);
		
		for(String tableName:tableNames){
			System.out.println(tableName);
		}
		
		mt.list(conn, tableNames);
		
		
        
	}
	protected String getType(String tableName){
		String  []segs  = tableName.split("_");
		
		String ret="";
		for(int i=0;i<segs.length-1;i++){
			
			ret = ret + segs[i].substring(0,1).toUpperCase()+segs[i].substring(1);
		}
		return ret;
		
		
		
	}
	
	

}
