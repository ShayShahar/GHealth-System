package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import client.entity.Appointment;
import common.entity.Request;
import common.enums.Result;

public class CreateAppointmentDB {
	
	
    public static Object handleMessage (Request request, Connection connection) {
    	
		ResultSet res = null;

		Appointment appointment = (Appointment) request.getEntity();
	
		String searchDate = "SELECT * FROM ghealth.dates WHERE dateDate=? AND specID=?";
		String insertAppointment = "INSERT INTO ghealth.appointments (appDate, appInviteDate, appTime, specialist, client) VALUES (?,NOW(),?,?,?)";
		String insertNewDate = "INSERT INTO ghealth.dates (dateDate, specID, appointments) VALUES (?,?,?)";
		String updateDate ="UPDATE ghealth.dates SET appointments=? WHERE dateDate=? AND specID=?";

		
		try {
			
			//create new appointment in db
		    PreparedStatement preparedStatement1 = connection.prepareStatement(insertAppointment);
		    preparedStatement1.setDate(1,appointment.getDate());
		    preparedStatement1.setInt(2,appointment.getTime());
		    preparedStatement1.setInt(3,appointment.getSpecialistID());
		    preparedStatement1.setInt(4,appointment.getClientID());

		    preparedStatement1.executeUpdate();
		    
		 //search for date in db
		    
		    PreparedStatement preparedStatement2 = connection.prepareStatement(searchDate);
		    preparedStatement2.setDate(1,appointment.getDate());
		    preparedStatement2.setInt(2,appointment.getSpecialistID());
		    
		    res = preparedStatement2.executeQuery();
		    
		  //in case this is the first appointment for the specialist at this date 		    
		    if (!res.next()) {
		    	
			    PreparedStatement preparedStatement3 = connection.prepareStatement(insertNewDate);
			    preparedStatement3.setDate(1,appointment.getDate());
			    preparedStatement3.setInt(2,appointment.getSpecialistID());
			    
			    StringBuilder appMask = new StringBuilder("000000000000000000");
			    appMask.setCharAt(appointment.getTime() - 1,'1');
			    preparedStatement3.setString(3, appMask.toString());

			    preparedStatement3.executeUpdate();
		    }
		    
		  //update existing date value in dates db  
		    else{
		    	
		    	String appStr = res.getString(3);
		    	StringBuilder addValue = new StringBuilder(appStr);
		    	addValue.setCharAt(appointment.getTime() - 1,'1');
		    	
		    	PreparedStatement preparedStatement4 = connection.prepareStatement(updateDate);
			    preparedStatement4.setDate(2,appointment.getDate());
			    preparedStatement4.setInt(3,appointment.getSpecialistID());
			    preparedStatement4.setString(1,addValue.toString());

			    preparedStatement4.executeUpdate();
			    
		    }
		    
		    return Result.OK;

		    
		} catch (SQLException e) {
		    e.printStackTrace();
		    return Result.ERROR;
		}
	}
	

}
