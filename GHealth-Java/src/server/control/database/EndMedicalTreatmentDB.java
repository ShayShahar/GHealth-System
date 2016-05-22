package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import common.entity.Request;
import common.enums.Result;


/**
 * EndMedicalTreatmentDB class updates the status of the appointment to finished and set the price of the appointment in the Data base.
 * @author shays
 *
 */

public class EndMedicalTreatmentDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
    	String endTreatment = "UPDATE ghealth.appointments SET appointments.appPrice=?, appointments.appStatus=1 WHERE appointments.appID=?";

			try{
			    PreparedStatement statement = connection.prepareStatement(endTreatment);    				
					statement.setInt(1, Integer.parseInt(request.getList().get(0)));
					statement.setInt(2, Integer.parseInt(request.getList().get(1)));
					statement.executeUpdate();
					
					return Result.OK;


			}catch(Exception e){
				e.printStackTrace();
				return Result.ERROR;
			}
	}
	
	
}
