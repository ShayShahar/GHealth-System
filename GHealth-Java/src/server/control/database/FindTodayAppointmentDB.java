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
	    	//12
			String searchAppointments = "SELECT * FROM ghealth.appointments WHERE specialist=? AND appDate=CURDATE() ORDER BY appTime ASC";
			ArrayList<Integer> list = new ArrayList<Integer>();
		   
		   	try{
			    PreparedStatement preparedStatement1 = connection.prepareStatement(searchAppointments);
			    ResultSet res;
			    preparedStatement1.setInt(1, Integer.parseInt(request.getList().get(0)));
			    
			    res = preparedStatement1.executeQuery();
			    
			    if (!res.next()){
			    	return Result.ERROR;
			    }
			    
			    do {
			    				    	
			    	list.add(res.getInt(4));
			    	
			    }while(res.next());
			    
			    return list;
				  
	    	} catch (SQLException e) {
						e.printStackTrace();
						return Result.ERROR;
				}
	    }
	    	
	   }

