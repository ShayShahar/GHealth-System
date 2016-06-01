package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class GetAppointmentReview.
 */
public class GetAppointmentReview {

	
	/**
	 * Handle message.
	 *
	 * @param request the request
	 * @param connection the connection
	 * @return the object
	 */
	public static Object handleMessage(Request request, Connection connection) {
		
		String findAppointment = "SELECT * FROM ghealth.appointments WHERE ghealth.appointments.appID=?";

		try{
				ArrayList<String> list = new ArrayList<String>();
				
    		PreparedStatement preparedStatement = connection.prepareStatement(findAppointment);    				
    		ResultSet result;
    		
    		preparedStatement.setInt(1,Integer.parseInt(request.getList().get(0)));
    		result = preparedStatement.executeQuery();
    		
    		
    		if(!result.next()){

    			return Result.ERROR;
    		}
    		
    		else{
    			if (result.getString(5) != null)
    				list.add(result.getString(5));
    			else{
    				list.add("none");
    			}
       	if (result.getString(6) != null)
    				list.add(result.getString(6));
    			else{
    				list.add("none");
    			}
    			list.add(Integer.toString(result.getInt(9)));

    		}
    		return list;

    		
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
		}
	}			
	
}
