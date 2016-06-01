package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class FindUserIdByUserNameDB.
 */
public class FindUserIdByUserNameDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */

	public static Object handleMessage(Request request, Connection connection) {
		int id;
		
		String stmnt = "SELECT ghealth.specialists.specialistID FROM ghealth.users , ghealth.specialists WHERE userName =? AND ghealth.users.personID=ghealth.specialists.personID";
	   	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;	
    		preparedStatement1.setString(1,request.getList().get(0));
    		result = preparedStatement1.executeQuery();
    		if(!result.next())
    			return Result.ERROR;
    			
    		id = result.getInt(1);
    
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return id;
	}
	
	

}
