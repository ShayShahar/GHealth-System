package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindTodayAppointmentDB {
	   public static Object handleMessage (Request request, Connection connection) {

		  String searchAppointments = "SELECT * FROM ghealth.users, ghealth.appointments, ghealth.specialists WHERE ghealth.users.userName=? AND ghealth.appointments.appDate=CURDATE() AND ghealth.users.personID=ghealth.specialists.personID ORDER BY ghealth.appointments.appTime ASC";
		 	ArrayList<Integer> list = new ArrayList<Integer>();
		   
		   	try{
			    PreparedStatement preparedStatement1 = connection.prepareStatement(searchAppointments);
			    ResultSet res;
			    preparedStatement1.setString(1,request.getList().get(0));
			    System.out.println(request.getList().get(0));
			    res = preparedStatement1.executeQuery();
			    
			    if (!res.next()){
			    	return Result.ERROR;
			    }
			    
			    do {
			    				    	
			    	list.add(res.getInt(9));
			    	
			    }while(res.next());
			    
			    return list;
				  
	    	} catch (SQLException e) {
						e.printStackTrace();
						return Result.ERROR;
				}
	    }
	    	
	   }

