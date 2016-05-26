package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.entity.Request;
import common.enums.Result;

/**
 * FindAppointmentsDB class search for the all coming appointments of a client in the Data base
 * @author shays
 *
 */

public class FindAppointmentsDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT ghealth.appointments.appID, ghealth.appointments.appDate, "
    				+ "ghealth.appointments.appTime, ghealth.person.personName, ghealth.person.personFamily, ghealth.specialists.branchName FROM ghealth.appointments, ghealth.clients, ghealth.specialists, ghealth.person"
    				+ " WHERE clients.person='" + request.getList().get(0) + "' AND ghealth.appointments.appDate > CURDATE() AND ghealth.clients.clientID=appointments.client AND "
    						+ "ghealth.appointments.specialist=ghealth.specialists.specialistID AND ghealth.specialists.personID=ghealth.person.personID");
    		
    		if (!result.next()){
    			return Result.NO_APPOINTMENTS_FOUND;
    		}
    		
    		ArrayList<Object> resultList = new ArrayList<Object>();

    		
    		do{
    		ArrayList<String> list = new ArrayList<String>();
    		    		
    		list.add(Integer.toString(result.getInt(1))); //appointment id
    		list.add((result.getDate(2)).toString()); //appointment date
    		list.add(Integer.toString(result.getInt(3))); //appointment time
    		list.add(result.getString(4) + " " + result.getString(5)); //set specialist name
    		list.add(result.getString(6)); //branch
    		
    		resultList.add(list);
    		
    		} while(result.next());
    		
      	
    		return resultList;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
	
	
}
