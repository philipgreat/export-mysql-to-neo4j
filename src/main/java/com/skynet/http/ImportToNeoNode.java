package com.skynet.http;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
/*
 * 
 * Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "neo4j?") );
		ticker.tick("driver");
		Session session = driver.session();
		ticker.tick("session");
		
		
		//session.run( "CREATE (a:Person {name:'Arthur', title:'King'})" );
		//ticker.tick("");
		StatementResult result = session.run( "MATCH (a:Person) WHERE a.name = 'Arthur' RETURN a.name AS name, a.title AS title" );
		t
 * */
public class ImportToNeoNode extends MySQLDataTraveler{

	private Session session;


	public ImportToNeoNode() {
		Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "neo4j?") );
		
		session = driver.session();
		
		
	}
	int count = 0;
	Transaction tx;
	public void handleRecord(String tableName,ResultSet rs) throws SQLException{
		String id = rs.getString("id");
		
		if(count%2000 == 0){
			tx = session.beginTransaction();
		}
		
		
		tx.run("create(:"+tableName+"{id:'"+id+"'})");
		
		if(count%2000 == 1999){
			tx.success();
			tx.close();
		}
		
		count++;
		
		
		System.out.println(tableName+":"+id);
	}
	
	
	public  static void main(String []args) throws ClassNotFoundException, SQLException{
		
		
		
		MySQLDataTraveler mt = new ImportToNeoNode();
		Connection conn  = mt.getConnection();
		List<String>tableNames = mt.getAllTables(conn);
		
		for(String tableName:tableNames){
			System.out.println(tableName);
		}
		Ticker t=new Ticker();
		mt.list(conn, tableNames);
		t.tick("import");
		
        
	}
}
