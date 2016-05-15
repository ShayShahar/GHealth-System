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
		  Integer id =3;
	    		String searchClient = "SELECT ghealth.clients.person FROM ghealth.appointments , ghealth.clients WHERE  ghealth.appointments.appDate=CURDATE() AND ghealth.appointments.specialist=?  AND ghealth.appointments.appTime=? AND ghealth.appointments.client=ghealth.clients.clientID";
	 		    String searchDetails = "SELECT * FROM ghealth.person WHERE  ghealth.person.personID=? ";

	    		 ArrayList<String> list = new ArrayList<String>();
	 		   
	 		   	try{
	 		   		
	 			    PreparedStatement preparedStatement1 = connection.prepareStatement(searchClient);
	 			    ResultSet res;
	 			    preparedStatement1.setString(1,request.getList().get(1));
	 			   preparedStatement1.setString(2,request.getList().get(0));
	 			  System.out.println(request.getList().get(0));
	 			 System.out.println(request.getList().get(1));
	 			    res = preparedStatement1.executeQuery();

	 			    if (!res.next()){
	 			   	System.out.println(id);
	 			    	return Result.ERROR;
	 			    }
		    	
	 			     id = res.getInt(1);
	 			    System.out.println(id);
	 			    	 
	 		 		   	try{
	 		 			    PreparedStatement preparedStatement2 = connection.prepareStatement(searchDetails);
	 		 			    ResultSet result;
	 		 			    preparedStatement2.setString(1,Integer.toString(id));

	 		 			  result = preparedStatement2.executeQuery();
	 		 			    
	 		 			    if (!result.next()){
	 		 			    	return Result.ERROR;
	 		 			    }
	 			    	
	 		 			  list.add(Integer.toString(result.getInt(1)));
	 		     		list.add(result.getString(2));
	 		     		list.add(result.getString(3));
	 		     		list.add(result.getString(4));
	 		     		list.add(Integer.toString(result.getInt(5)));
	 		     		list.add(result.getString(6));
	 		 			    
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
