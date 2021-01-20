package dev.gec.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBConnection
{
	//SINGLETON
	//This class uses a single Connection object.  We make and return a connection if it doesn't exist.
	//If it exists, we instead return the existing connection.
	
	private static Connection con = null;
	
	//public 'getter' of private Connection object
	public static Connection getConnection()
	{
		try
		{
			//Make a new connection if none exists.
			if(con == null)
			{
				Class.forName("oracle.jdbc.driver.OracleDriver"); //Oracle used this to ensure its drivers correctly loaded.
				
				//To make a connection, we need our endpoint's URL, username, and password.
				//A late resort is to hardcode login details. 
				
				Properties props = new Properties();
				FileInputStream inStream = new FileInputStream("src/main/resources/connection.properties"); //Short-term solution!  Doesn't work on servers! 
				
//				FileInputStream inStream = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile()); //Second approach:  Based on included file.
				props.load(inStream);
				
				String URL = props.getProperty("URL");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
//				String URL=props.getProperty(URL), username=props.getProperty(username), password=props.getProperty(password);
				
				//Environment Variables from System
//				String envURL=System.getenv("rds_url"), envUsername=System.getenv("rds_username"), envPassword=System.getenv("rds_password");
				
				//Establish our connection!
				con = DriverManager.getConnection(URL, username, password);
				
				return con;
			}
			
			//Return the current connection if it exists.
			else
			{
				return con;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally //Optional
		{
			;
		}
		return null;
	}
	
	//For testing purposes ONLY, NOT to use JDBC!
	public static void main(String[] args)
	{
		Connection conny = getConnection();
		System.out.println("Connection conny: " + con);
	}
}