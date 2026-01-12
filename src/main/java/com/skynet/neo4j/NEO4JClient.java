package com.skynet.neo4j;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.skynet.http.Ticker;

public class NEO4JClient {
	//private Session session;
	Driver driver ;

	public NEO4JClient() {
		driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "neo4j?") );
		
		
		
		
	}
	
	public void testRelation(){
		
		
		//StatementResult result = session.run( query,parameters);
		//runCheckQuery
		StatementResult result = runCheckQuery("TaskViolation","TV000001","DecorationCountryCenter","DCC000002");
		
		//ticker.tick("query");
		while ( result.hasNext() )
		{
		    Record record = result.next();
		    System.out.println( record.get( "extracted" ) );
		}
		
		System.out.println();
		
		
	}
	protected void checkType(String type)
	{
		

		//the first char must be alphabets
		String trimedType = type.trim();
		if(trimedType.isEmpty()){
			throw new IllegalArgumentException("protected void checkType(String type): type is not allowed to be empty after trimed");
		}
		char firstChar = trimedType.charAt(0);//safe here, empty is checked;
		if(!Character.isLetter(firstChar)){
			throw new IllegalArgumentException("protected void checkType(String type): first char '"+firstChar+"' must be alphabets");
		}
		
		for(char ch: type.toCharArray()){
			if(Character.isDigit(ch)){
				continue;
			}
			if(Character.isLetter(ch)){
				continue;
			}
			throw new IllegalArgumentException("Character '"+ch+"' in " + type + " is not allowed to be other character except letter and digit");
			
		}
	}
	protected StatementResult runCheckQuery(String startType, String startId, String endType, String endId)
	{
		
		checkType(startType);
		checkType(endType);
		
		String query = "MATCH (:"+startType+" {id:{startId}})-[rel*..10]->(:"+endType+" {id:{endId}}) "
				+"return extract( r IN rel | type(r))  as extracted";
		
		System.out.println(query);
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("startId", startId);
		parameters.put("endId", endId);
		Session session = driver.session();
		return session.run( query,parameters);
	}
	
	
	
	//DCC000001->DPC000004->DA000001->DM000033
	public  static void main(String []args) throws ClassNotFoundException, SQLException{
		
		TokenTool tool = new TokenTool();
		//System.out.println("v: " + ((213>>6) & 3));
		//System.out.println("v: " + ((213>>4) & 3));
		//System.out.println("v: " + ((213) & 3));
		//System.out.println("v: " + Integer.toBinaryString(213));
		
		System.out.println("v: " + tool.decode("MXWR"));
		//decodeTokens
		System.out.println("v: " +tool.decodeTokens(tool.decode("MXWR")));
		
		if(true){
			NEO4JClient client = new NEO4JClient();
			
			Ticker ticker = new Ticker();
			client.testRelation();
			ticker.tick("client.testRelation(); first time");
			
			for(int i=0;i<1;i++){
				client.testRelation();
			}
			
			ticker.tick("client.testRelation();");
		}
		
	}
	
	
}
