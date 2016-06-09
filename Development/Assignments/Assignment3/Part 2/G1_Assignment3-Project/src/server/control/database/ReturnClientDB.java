package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class ReturnClientDB.
 */
public class ReturnClientDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
		
		String updateStatus = "UPDATE ghealth.clients SET ghealth.clients.clientStatus=1 WHERE ghealth.clients.clientID=?";
		
		try {
		    	
		    	PreparedStatement preparedStatement = connection.prepareStatement(updateStatus);
		    	preparedStatement.setInt(1,Integer.parseInt(request.getList().get(0)));
			    preparedStatement.executeUpdate();

			    return Result.OK;
			   
	
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return Result.ERROR;
		}
	}
	
	
}
