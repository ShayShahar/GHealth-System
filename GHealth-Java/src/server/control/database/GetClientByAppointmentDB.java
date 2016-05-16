package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class GetClientByAppointmentDB {
	  public static Object handleMessage (Request request, Connection connection) {
		  Integer id ,spId;
	    		String searchClient = "SELECT ghealth.clients.person, ghealth.clients.clientID, ghealth.clients.clientClinic  FROM ghealth.appointments , ghealth.clients WHERE  ghealth.appointments.appDate=CURDATE() AND ghealth.appointments.specialist=?  AND ghealth.appointments.appTime=? AND ghealth.appointments.client=ghealth.clients.clientID";
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
	 			  System.out.println(request.getList().get(0));
	 			 System.out.println(request.getList().get(1));
	 			    res = preparedStatement1.executeQuery();

	 			    if (!res.next()){
	 			   
	 			    	return Result.ERROR;
	 			    }
		    	
	 			     id = res.getInt(1);
	 			   list.add(Integer.toString(id));
	 			   list.add(Integer.toString(res.getInt(2)));
	 			   list.add(res.getString(3));
	 			 //  String joinDate = res.getString(5);
	 			    	 
	 		 		   	try{
	 		 			    PreparedStatement preparedStatement2 = connection.prepareStatement(searchDetails);
	 		 			    ResultSet result;
	 		 			    preparedStatement2.setString(1,Integer.toString(id));

	 		 			  result = preparedStatement2.executeQuery();
	 		 			    
	 		 			    if (!result.next()){
	 		 			    	return Result.ERROR;
	 		 			    }
	 			    	
	 		 			list.add(Integer.toString(res.getInt(1)));
	 		     		list.add(result.getString(2));
	 		     		list.add(result.getString(3));
	 		     		list.add(result.getString(4));
	 		     		list.add(result.getString(5));
	 		     		list.add(result.getString(6));
	 		     	//	list.add(joinDate);
	 		 			    
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
