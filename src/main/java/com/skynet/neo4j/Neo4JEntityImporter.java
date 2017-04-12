package com.skynet.neo4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skynet.http.MySQLDataTraveler;
import com.skynet.http.Ticker;

public class Neo4JEntityImporter extends Neo4JRelationImporter {

	public Neo4JEntityImporter() {
		// TODO Auto-generated constructor stub
	}
	
	protected List<String> getCypherStatement(String tableName,ResultSet rs) throws SQLException{
		//MATCH (a :accelerator_account_data {id:"AA000001"}),(b :accelerator_account_data{id:"AA000002"})
		//create (a)-[:REFERENCE]->(b)
		if(relation == null){
			relation = new CustomRelation();
		}
		String fromType = getType(tableName);
		
		
		
		List<String> statements = new ArrayList<String>();
		String id = rs.getString("id");
		
		String statement =String.format("create (:%s{id:\"%s\"})",fromType,id );
		statements.add(statement);
		return statements;
		
	}
	public  static void main(String []args) throws ClassNotFoundException, SQLException{
		
		
		
		MySQLDataTraveler mt = new Neo4JEntityImporter();
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
