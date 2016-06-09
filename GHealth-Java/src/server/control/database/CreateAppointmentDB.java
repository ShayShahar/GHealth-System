package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import client.entity.Appointment;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * CreateAppointmentDB class creates an appointment in the Data base.
 *
 * @author shays
 */

public class CreateAppointmentDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
		ResultSet res = null;
		boolean flag = false;
		String appStr = "";
		Appointment appointment = (Appointment) request.getEntity();
	
		String searchDate = "SELECT * FROM ghealth.dates WHERE dateDate=? AND specID=?";
		String insertAppointment = "INSERT INTO ghealth.appointments (appDate, appInviteDate, appTime, specialist, client) VALUES (?,NOW(),?,?,?)";
		String insertNewDate = "INSERT INTO ghealth.dates (dateDate, specID, appointments) VALUES (?,?,?)";
		String updateDate ="UPDATE ghealth.dates SET appointments=? WHERE dateDate=? AND specID=?";
		String searchAppointment = "SELECT * FROM ghealth.appointments " + 
																	 "WHERE client = ? AND appDate = ? AND appTime = ?";

		String searchAppointmentForSpec = "SELECT * FROM ghealth.appointments " + 
				 "WHERE specialist = ? AND appDate = ? AND appTime = ?";
		
		try {
			
			//search for the date in db
		    PreparedStatement preparedStatement = connection.prepareStatement(searchDate);
		    preparedStatement.setDate(1,appointment.getDate());
		    preparedStatement.setInt(2,appointment.getSpecialistID());
		    
		    res = preparedStatement.executeQuery();
		    
		    if (res.next()){
		    	flag = true;
		    	appStr = res.getString(3);
		    	if (appStr.charAt(appointment.getTime() - 1) == '1'){
				    return Result.ERROR;
		    	}
		    }
		    
		    
			  //check if the client have another appointment in same date and hour
		    PreparedStatement preparedStatement5 = connection.prepareStatement(searchAppointmentForSpec);
		    preparedStatement5.setInt(1,appointment.getSpecialistID());
		    preparedStatement5.setDate(2,appointment.getDate());
		    preparedStatement5.setInt(3,appointment.getTime());
			
		    res = preparedStatement5.executeQuery();
		    
		    if (res.next()){
		    	return Result.FAILED;
		    }
		    
		    
		    
		  //check if the client have another appointment in same date and hour
		    PreparedStatement preparedStatement2 = connection.prepareStatement(searchAppointment);
		    preparedStatement2.setInt(1,appointment.getClientID());
		    preparedStatement2.setDate(2,appointment.getDate());
		    preparedStatement2.setInt(3,appointment.getTime());
			
		    res = preparedStatement2.executeQuery();
		    
		    if (res.next()){
		    	return Result.FAILED;
		    }

		    
			//create new appointment in db
		    PreparedStatement preparedStatement1 = connection.prepareStatement(insertAppointment);
		    preparedStatement1.setDate(1,appointment.getDate());
		    preparedStatement1.setInt(2,appointment.getTime());
		    preparedStatement1.setInt(3,appointment.getSpecialistID());
		    preparedStatement1.setInt(4,appointment.getClientID());

		    preparedStatement1.executeUpdate();
		   
 
		  //in case this is the first appointment for the specialist at this date 		    
		    if (flag == false) {
		    	
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
