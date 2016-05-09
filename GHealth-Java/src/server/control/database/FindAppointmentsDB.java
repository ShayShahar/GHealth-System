package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.entity.Request;
import common.enums.Result;

public class FindAppointmentsDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.appointments, ghealth.clients, ghealth.specialists, ghealth.person"
    				+ " WHERE clients.person='" + request.getList().get(0) + "' AND ghealth.appointments.appDate >= NOW() AND ghealth.clients.clientID=appointments.client AND "
    						+ "ghealth.appointments.specialist=ghealth.specialists.specialistID AND ghealth.specialists.personID=ghealth.person.personID");
    		
    		if (!result.next()){
    			return Result.NO_APPOINTMENTS_FOUND;
    		}
    		
    		ArrayList<Object> resultList = new ArrayList<Object>();

    		
    		do{
    		ArrayList<String> list = new ArrayList<String>();
    		    		
    		list.add(Integer.toString(result.getInt(1))); //appointment id
    		list.add((result.getDate(2)).toString()); //appointment date
    		list.add(Integer.toString(result.getInt(4))); //appointment time
    		list.add(result.getString(19) + " " + result.getString(20)); //set specialist name
    		list.add(result.getString(17)); //branch
    		
    		resultList.add(list);
    		
    		} while(result.next());
    		
      	
    		return resultList;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
	
	
}