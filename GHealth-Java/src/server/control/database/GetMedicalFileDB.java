package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.entity.Request;
import common.enums.Result;


/**
 * GetMedicalFileDB class search for all medical information of a client in the Data base
 * @author shays
 *
 */

public class GetMedicalFileDB {


	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
    	ResultSet result = null;
    	    	
    	String searchAppointments = "SELECT appointments.appDate, specialists.specialistType, person.personName, person.personFamily "
    			+  "FROM appointments, specialists, person "
    			+  "WHERE appointments.appDate < CURDATE() AND appointments.client=? AND appointments.specialist = specialists.specialistID AND specialists.personID = person.personID "
    			+  "ORDER BY appointments.appDate";
    	
    	String searchReferences = "SELECT reference.refDate, specialists.specialistType, person.personName, person.personFamily " +
    																"FROM reference, specialists, person " + 
    																"WHERE reference.client_id=? AND reference.specialist_id = specialists.specialistID AND specialists.personID = person.personID "
    															+ "ORDER BY reference.refDate";
    	
    	ArrayList<Object> list = new ArrayList<Object>();
    	
    	try{
    			    
		    PreparedStatement preparedStatement = connection.prepareStatement(searchAppointments);
		    preparedStatement.setInt(1,Integer.parseInt(request.getList().get(0)));
    		
		    result = preparedStatement.executeQuery();
		    
		    while (result.next()){
		    	ArrayList<String> object = new ArrayList<String>();
		    	object.add(result.getDate(1).toString());
		    	object.add(result.getString(2));
		    	object.add(result.getString(3));
		    	object.add(result.getString(4));
		    	object.add("Appointment");

		    	list.add(object);
		    }
		    
		    PreparedStatement preparedStatement2 = connection.prepareStatement(searchReferences);
		    preparedStatement2.setInt(1,Integer.parseInt(request.getList().get(0)));
    		
		    result = preparedStatement2.executeQuery();
		    
		    while (result.next()){
		    	ArrayList<String> object = new ArrayList<String>();
		    	object.add(result.getDate(1).toString());
		    	object.add(result.getString(2));
		    	object.add(result.getString(3));
		    	object.add(result.getString(4));
		    	object.add("Reference");

		    	list.add(object);
		    }
		    
		    return list;
		    
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
			
    }
    	
    	
	
	
}
