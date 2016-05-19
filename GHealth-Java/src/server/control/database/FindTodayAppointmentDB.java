package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindTodayAppointmentDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	   public static Object handleMessage (Request request, Connection connection) {

		  String searchAppointments =
				  														"SELECT appointments.appTime " +
				  														"FROM ghealth.users, ghealth.appointments, ghealth.specialists " +
				  														"WHERE ghealth.users.userName=? AND ghealth.appointments.appDate=CURDATE() " +
				  														"AND ghealth.users.personID=ghealth.specialists.personID " +
				  														"AND ghealth.appointments.specialist=ghealth.specialists.specialistID ORDER BY ghealth.appointments.appTime ASC";

		   ArrayList<Integer> list = new ArrayList<Integer>();
		   
		   	try{
			    PreparedStatement preparedStatement1 = connection.prepareStatement(searchAppointments);
			    ResultSet res;
			    preparedStatement1.setString(1,request.getList().get(0));
			    res = preparedStatement1.executeQuery();
			    
			    if (!res.next()){
			    	return Result.ERROR;
			    }
			    
			    do {
			    				    	
			    	list.add(res.getInt(1));
			    	
			    }while(res.next());
			    
			    return list;
				  
	    	} catch (SQLException e) {
						e.printStackTrace();
						return Result.ERROR;
				}
	    }
	    	
	   }

