package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class InsertRecordDB.
 */
public class InsertRecordDB {

	/**
	 * Handle message.
	 *
	 * @param request the request
	 * @param connection the connection
	 * @return the object
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
