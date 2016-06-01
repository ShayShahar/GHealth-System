package server.control.database;

import java.sql.*;

import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * LogoutDB class updates user status to logged out in the Data base.
 *
 * @author shays
 */

public class LogoutDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {

			Statement stmt;
			try {
				stmt = connection.createStatement();
				stmt.executeUpdate("UPDATE ghealth.users SET userStatus=0 WHERE userName='" + request.getList().get(0) + "'");
				
				return Result.LOGGEDOUT;
	
			} catch (SQLException e1) {
				e1.printStackTrace();
				return Result.ERROR;
			}
	    	
    }

	
}
