package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import common.entity.Request;
import common.enums.Result;

public class CancelAppointmentDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
		ResultSet res = null;
		ResultSet res2 = null;

		
		String searchAppointment = "SELECT * FROM ghealth.appointments WHERE appID=?";
		String deleteAppointment = "DELETE FROM ghealth.appointments WHERE appID=?";
		String searchDate = "SELECT * FROM ghealth.dates WHERE dateDate=? AND specID=?";
		String deleteDate = "DELETE FROM ghealth.dates WHERE dateDate=? AND specID=?";
		String updateDate = "UPDATE ghealth.dates SET appointments=? WHERE dateDate=? AND specID=?";
		
	
		try {
		    PreparedStatement preparedStatement1 = connection.prepareStatement(searchAppointment);
		    preparedStatement1.setInt(1,Integer.parseInt(request.getList().get(0)));
		    res = preparedStatement1.executeQuery();
	
		    if (!res.next()) {
		    	res.close();
		    	return Result.ERROR;
		    }
		    		    
		    java.sql.Date date = res.getDate(2);
		    java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		    java.sql.Date tomorrow = new java.sql.Date(Calendar.getInstance().getTime().getTime() + (1000 * 60 * 60 * 24));
		    
		    if (tomorrow.compareTo(date) == 1 || today.compareTo(date) == 0){
		    	return Result.NEXT_24;
		    }
		    
		    	
		    PreparedStatement preparedStatement2 = connection.prepareStatement(searchDate);
		    preparedStatement2.setDate(1,res.getDate(2));
		   	preparedStatement2.setInt(2,res.getInt(8));
		    res2 = preparedStatement2.executeQuery();
			   
			    
	      if (!res2.next()) {
	    	  res.close();
			   	return Result.ERROR;
			  }
			    
			    
			  String checkAppointments = res2.getString(3);
		  	int appointmentTime = res.getInt(4);
			    				    	
		  	StringBuilder builder = new StringBuilder(checkAppointments);
		  	builder.setCharAt(appointmentTime - 1, '0');
			    	
			  if (builder.toString().equals("000000000000000000")){
				  PreparedStatement preparedStatement3 = connection.prepareStatement(deleteDate);
				  preparedStatement3.setDate(1, res.getDate(2));
				  preparedStatement3.setInt(2, res.getInt(8));
				  preparedStatement3.executeUpdate(); 	
			  }
			    	
			  else{
				  PreparedStatement preparedStatement4 = connection.prepareStatement(updateDate);
			    preparedStatement4.setString(1, builder.toString());
			    preparedStatement4.setDate(2,res.getDate(2));
			   	preparedStatement4.setInt(3, res.getInt(8));
	   	  	preparedStatement4.executeUpdate();
	   	  	}		    
		   
			 	PreparedStatement preparedStatement5 = connection.prepareStatement(deleteAppointment);
			 	preparedStatement5.setInt(1, Integer.parseInt(request.getList().get(0)));
	    	preparedStatement5.executeUpdate();

	    	return Result.OK;  
		    
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return Result.ERROR;
		}
		
	}
	
	
}