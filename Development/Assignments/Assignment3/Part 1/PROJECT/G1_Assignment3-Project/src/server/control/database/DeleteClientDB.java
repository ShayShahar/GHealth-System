package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

/**
 * DeleteClientDB used to clear new clients that created with JUnit
 * @author shays
 *
 */
public class DeleteClientDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
    public static Object handleMessage (Request request, Connection connection) {
    	
		String deletePerson = "DELETE FROM ghealth.person WHERE personID=?";
		String deleteClient = "DELETE FROM ghealth.clients WHERE person=?";

		
	
		try {
		    PreparedStatement preparedStatement1 = connection.prepareStatement(deleteClient);
		    preparedStatement1.setString(1,request.getList().get(0));
		    preparedStatement1.executeUpdate();

		    	
		    PreparedStatement preparedStatement2 = connection.prepareStatement(deletePerson);
		    preparedStatement2.setString(1,request.getList().get(0));
		    preparedStatement2.executeUpdate();
		    
		    
	    	return Result.OK;  
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		    return Result.ERROR;
		}
		
	}
	
}
