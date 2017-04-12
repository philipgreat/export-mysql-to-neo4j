package com.skynet.http;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;



public class RemoteNeo4j {

	public RemoteNeo4j() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		
		Ticker ticker = new Ticker();
		
		// TODO Auto-generated method stub
		Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "neo4j?") );
		ticker.tick("driver");
		Session session = driver.session();
		ticker.tick("session");
		
		
		//session.run( "CREATE (a:Person {name:'Arthur', title:'King'})" );
		//ticker.tick("");
		StatementResult result = session.run( "MATCH (a:Person) WHERE a.name = 'Arthur' RETURN a.name AS name, a.title AS title" );
		ticker.tick("query");
		while ( result.hasNext() )
		{
		    Record record = result.next();
		    System.out.println( record.get( "title" ).asString() + " " + record.get("name").asString() );
		}
		ticker.tick("fetch");
		session.close();
		driver.close();
		ticker.tick("clean");
		
	}

}
