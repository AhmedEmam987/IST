package com.ist.dpc.mainivr.integration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ist.dpc.mainivr.configuration.C;
import com.ist.tools.v2.logger.Logger;

public class GetAndSetData {
	
	public GetAndSetData(){};
	
	static Logger LOGGER = Logger.getLogger(GetAndSetData.class);
	static String TakeResult =null;
	
public  static Connection dbconnection  () {
 		
 		Connection connection= null;
 		
 		try {
 			connection=C.CONFIGURATOR.getConnection("database");
 			if (connection !=null)
 				LOGGER.info("Connection Successful");
 			return connection;
 		}
 		
 	 catch (SQLException e) {
 		LOGGER.error("error", e);
 		return null;
 	}
 		
 		
 		 	}
public static String getQueryResult(String Query) throws Exception{

			Connection connection= dbconnection  ();
		 try {
	 			Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(Query);
		        boolean result = resultSet.next();
		        TakeResult = resultSet.getString(1);
		      if (result)
		      {
		        LOGGER.info("data collected successfully");
		           
		      }
	 			
	 		}
	 		
	 	 catch (Exception e) {
	 		LOGGER.error("the search for input not found");
	 		
	 	}
		 
	    connection.close();
	    return TakeResult;
	   
	}
	
		
		public static void setQuery(String Query) throws Exception{

			Connection connection= dbconnection  ();
			 try {
		 					 			
		 			Statement statement = connection.createStatement();
			    	
			        boolean result =statement.execute(Query);
			     
			      if (result)
			      {
			        LOGGER.info("data set successfully");
			           
			      }
		 			
		 		}
		 		
		  catch (Exception e) {
		 		LOGGER.error("error", e);
		 		 	}
		    connection.close();
		    		    		 				
		}
		
	
	
	public static void setPreferedLanguage (String mobile , String PreferredLanguage ){
		
		try {
			setQuery("INSERT INTO PREFEREDLANGUAGE (MOBILE , LANGUAGE) VALUES ("+"'"+mobile+"'"+","+"'"+PreferredLanguage+"'"+ " ); ");
			LOGGER.info("Insertion Done Successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("error insertion PREFEREDLANGUAGE  ", e);
		}
		}
	
	public static String getPreferedLanguage (String mobile){	
	
		String PreferredLanguage = null;
		try {
			PreferredLanguage = getQueryResult("SELECT LANGUAGE from PREFEREDLANGUAGE where MOBILE = " + mobile + " ;");
			LOGGER.info("Prefered Language"+PreferredLanguage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("error getting PREFEREDLANGUAGE  ", e);
			
		}
		
		return PreferredLanguage;
	
	}

	public static String getOutageNotificationStatus (String outage){	
		
		String status=null;
		try {
			status = getQueryResult("SELECT STATUS from OUTAGENOTIFICATION where NAME = " +"'"+ outage +"'"+ " ;");
			if (status !=null)
			LOGGER.info("Outage Status"+status);
		} catch (Exception e) {
			LOGGER.error("error getting outage STATUS  ", e);
		}
		
		return status;
	
	}
	public static void sendSMS (String mobile){
			
	}
	
	
	
	/*
	 public static void main(String[] args)
{
		 com.ist.dpc.mainivr.integration.GetAndSetData lang = new com.ist.dpc.mainivr.integration.GetAndSetData();
		 //setPreferedLanguage("0100056789","ar");
	System.out.println(getOutageNotificationStatus("outage"));	
	}
	*/
}
