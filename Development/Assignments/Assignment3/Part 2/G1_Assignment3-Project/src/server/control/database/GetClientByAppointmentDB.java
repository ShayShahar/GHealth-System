package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class GetClientByAppointmentDB.
 */
public class GetClientByAppointmentDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	  public static Object handleMessage (Request request, Connection connection) {
		  
		  	Integer spId;
		  	String id;
		  	int appId;
		  	String searchClient = "SELECT ghealth.clients.person, ghealth.clients.clientID, ghealth.clients.clientClinic ,ghealth.appointments.appID FROM ghealth.appointments , ghealth.clients WHERE  ghealth.appointments.appDate=CURDATE() AND ghealth.appointments.specialist=?  AND ghealth.appointments.appTime=? AND ghealth.appointments.client=ghealth.clients.clientID";
	   		String searchDetails = "SELECT * FROM ghealth.person WHERE  ghealth.person.personID=? ";
	   		String spIdSt = "SELECT ghealth.specialists.specialistID FROM ghealth.users , ghealth.specialists WHERE userName =? AND ghealth.users.personID=ghealth.specialists.personID";
	   		ArrayList<String> list = new ArrayList<String>();
	    		 
	    			try{

	    	    		PreparedStatement statement = connection.prepareStatement(spIdSt);    				
	    	    		ResultSet res1;	
	    	    		
	    	    		statement.setString(1,request.getList().get(1));
	    	    		res1 = statement.executeQuery();
	    	    		if(!res1.next())
	    	    			return Result.ERROR;
	    	    			
	    	    		spId = res1.getInt(1);
	    	    
	    				  
	    	    	} catch (SQLException e) {
	    						e.printStackTrace();
	    						return Result.ERROR;
	    				}
	 		   
	 		   	try{
	 		   		
	 			    PreparedStatement preparedStatement1 = connection.prepareStatement(searchClient);
	 			    ResultSet res;
	 			    preparedStatement1.setString(1,Integer.toString(spId));
	 			    preparedStatement1.setString(2,request.getList().get(0));
	 			    res = preparedStatement1.executeQuery();

	 			    if (!res.next()){
	 			   
	 			    	return Result.ERROR;
	 			    }
		    	
	 			    id = res.getString(1);
	 			    list.add(id);
	 			    list.add(Integer.toString(res.getInt(2)));
	 			    list.add(res.getString(3));
	 			    appId = res.getInt(4);
	 			    	 
	 		 		   	try{
	 		 		   		
	 		 			    PreparedStatement preparedStatement2 = connection.prepareStatement(searchDetails);
	 		 			    ResultSet result;
	 		 			    preparedStatement2.setString(1,id);

	 		 			    result = preparedStatement2.executeQuery();
	 		 			    
	 		 			    if (!result.next()){
	 		 			    	return Result.ERROR;
	 		 			    }
	 		 			    list.add(result.getString(2));
	 		 			    list.add(result.getString(3));
	 		 			    list.add(result.getString(4));
	 		 			    list.add(result.getString(5));
	 		 			    list.add(result.getString(6));
	 		 			    list.add(Integer.toString(appId));
	 		 			    
	 		 			    return list;
	 		 				  
	 		 	    	} catch (SQLException e) {
	 		 						e.printStackTrace();
	 		 						return Result.ERROR;
	 		 				}
	 		 	    
	 	    	} catch (SQLException e) {
	 						e.printStackTrace();
	 						return Result.ERROR;
	 				}
	 	 }
}
