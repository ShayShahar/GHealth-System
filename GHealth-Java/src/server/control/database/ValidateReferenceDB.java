package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

/**
 * ValidateReferenceDB search for approved clinic's references in the Data base.
 * Checks if the person's ID is linked to the approved reference number.
 * @author shays
 *
 */
public class ValidateReferenceDB {

	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
    	
    	String validate = "SELECT * FROM ghealth.approvalreference "
    			+ "WHERE ghealth.approvalreference.appRefClientID=? AND ghealth.approvalreference.appRefID=?";

    	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(validate);    				
    		ResultSet result;	
    		preparedStatement1.setInt(1,Integer.parseInt(request.getList().get(0)));
    		preparedStatement1.setInt(2,Integer.parseInt(request.getList().get(1)));
    		result = preparedStatement1.executeQuery();
    		
    		if (!result.next()){
    			return Result.FAILED;
    		}
    		
			return Result.OK;
			
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }	
	
}
