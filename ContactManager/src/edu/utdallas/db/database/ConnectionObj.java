package edu.utdallas.db.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This Class will provide connection instance from static factory method
 * Connection can be explicitly closed by calling close on external Utility Methods
 * 
 * @version 1.0
 * @since v1.0
 * @author Varun Dani 
 *
 */
public class ConnectionObj {
	
	 	// JDBC driver name and database URL
	   static  String JDBC_DRIVER = null;
	   static  String DB_URL = null;

	   //  Database credentials
	   static  String USER = null;
	   static  String PASS = null;
	   
	   
	   
	   /**
	    * Private Constructor for Singleton Object
	    */
	   private ConnectionObj()
	   {
		   
	   }
	   
	   static
	   {
		   	Properties prop = new Properties();
		   try
		   {

			// load a properties file
			prop.load(ConnectionObj.class.getResourceAsStream("/DBProperties.properties"));

			// get the property value and print it out
			JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
			DB_URL = prop.getProperty("DB_URL");
			USER = prop.getProperty("USER");
			PASS = prop.getProperty("PASS");
			   
		   }
		   catch(Exception e)
		   {
			   System.out.println("Problem in Loading Database Propert File");
			   e.printStackTrace();
		   }
	   }
	   /**
	    * Public Factory Method for getting sql connection 
	    * @return ConnectionObj
	    */
	   public static Connection getConnection()
	   {
		   Connection conn = null;
		   
		   try
		   {
			   	  //Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");

			      //Open a connection
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		   }
		   catch(SQLException se)
		   {
			   System.out.println("SQl Error occured while opening Connection");
			   se.printStackTrace();
		   }
		   catch(Exception se)
		   {
			   System.out.println("Error occured while Connecting Database");
			   se.printStackTrace(); 
		   }
		   
		   return conn;
	   }
	   
	   
	
	   
}
