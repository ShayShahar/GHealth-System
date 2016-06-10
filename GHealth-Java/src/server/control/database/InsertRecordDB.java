package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class InsertRecordDB adds an appointment record to appointment instance in db.
 */
public class InsertRecordDB {


	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	public static Object handleMessage(Request request, Connection connection) {


		String updateAppointment = "UPDATE ghealth.appointments SET ghealth.appointments.appReview=? WHERE ghealth.appointments.appID=?";
		
		try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(updateAppointment);    				
	
    		preparedStatement1.setString(1,request.getList().get(0));
    		preparedStatement1.setString(2,request.getList().get(1));
    		preparedStatement1.executeUpdate();

    	
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		
		return Result.OK;
	}

}
