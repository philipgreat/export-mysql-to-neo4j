package com.skynet.neo4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;

import com.skynet.http.MySQLDataTraveler;
import com.skynet.http.Ticker;

public class Neo4JRelationImporter extends MySQLDataTraveler{

	private Session session;


	public Neo4JRelationImporter() {
		Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "neo4j?") );
		
		session = driver.session();
		
		
	}
	int count = 0;
	Transaction tx;
	
	/*
	 * 
	 * if(count%2000 == 0){
			tx = session.beginTransaction();
		}
		
		
		tx.run("create(:"+tableName+"{id:'"+id+"'})");
		
		if(count%2000 == 1999){
			tx.success();
			tx.close();
		}
		
	 * 
	 * */
	public void handleRecord(String tableName,ResultSet rs) throws SQLException{
		//String id = rs.getString("id");
		
		List<String> cypherSQLs = getCypherStatement(tableName,rs);
		if(cypherSQLs==null){
			return;
		}
		
		if(count%2000 == 0){
			tx = session.beginTransaction();
		}
		
		for(String cypher :cypherSQLs ){			
			tx.run(cypher);
		}
		
		
		if(count%2000 == 1999){
			tx.success();
			tx.close();
		}
		
		
		count++;
		//System.out.println(tableName+":"+id + ":" + cypherSQL);
	}
	BaseRelation relation ;
	protected List<String> getCypherStatement(String tableName,ResultSet rs) throws SQLException{
		//MATCH (a :accelerator_account_data {id:"AA000001"}),(b :accelerator_account_data{id:"AA000002"})
		//create (a)-[:REFERENCE]->(b)
		if(relation == null){
			relation = new CustomRelation();
		}
		String fromType = getType(tableName);
		String types[]=relation.getRelationIndex(fromType);
		if(types == null){
			return null;
		}
		
		
		List<String> statements = new ArrayList<String>();
		String id = rs.getString("id");
		
		for(String type: types){
			String targetId = rs.getString(relation.getTableFieldName(type));
			String targetField = relation.getBeanFieldName(type);
			String relationType = relation.getRelation(fromType, id, targetField, targetId);
			String statement = String.format("MATCH (a :%s {id:\"%s\"}),(b :%s{id:\"%s\"}) Create (a)-[:%s]->(b)",
					fromType,id,relation.getFieldType(type),targetId,relationType
					);
					
					
			statements.add(statement);
			
			System.out.println(statement);
		}

		return statements;
		
	}
	
	protected String getType(String tableName){
		String  []segs  = tableName.split("_");
		
		String ret="";
		for(int i=0;i<segs.length-1;i++){
			
			ret = ret + segs[i].substring(0,1).toUpperCase()+segs[i].substring(1);
		}
		return ret;
		
		
		
	}
	
	/* c   b a 
	 * 关系表示方法  {
	 * 	table_name: {
	 * 		fieldName: relation
	 * }
	 * 
	 * }
	 * 
	 * 
	 * */
	public  static void main(String []args) throws ClassNotFoundException, SQLException{
		
		
		
		MySQLDataTraveler mt = new Neo4JRelationImporter();
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
