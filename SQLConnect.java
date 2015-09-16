/*
 * This class simply shows how to connect to a localhost mysql database and select a column from a table.
 * 
 *  In this example I have select all the results from the name column in a table called stations.
 *  
 *  You can use this in an IDE but please to reference a mysql-connector.jar, to do this in elclipse simply;
 *  
 *  Download a connector/j from http://dev.mysql.com/downloads/connector/j/
 *  Right click on the project,
 *  Select Build Path then Configure Build Path...
 *  Click on add jar and find the connector j you have just installed (normally on Program Files (x86)/ MySql)
 *  
 */

package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class SQLConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://127.0.0.1/";
		String dbName = "bikeApp";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		
		try{
			Class.forName(driver).newInstance();
			
			Connection conn = DriverManager.getConnection(url+dbName,userName,"");
			
			//Using prepared statement is safer
			PreparedStatement preStatement = (PreparedStatement)conn.prepareStatement("Select * from stations");
			
			ResultSet res = preStatement.executeQuery("select * from stations");
			
			while(res.next())
			{
			System.out.println("Name: " + res.getString("name"));
			}
			
			
			conn.close();
			System.out.println("Success");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
