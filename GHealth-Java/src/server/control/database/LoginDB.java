package server.control.database;

import java.sql.*;

import common.entity.Request;
import common.enums.Result;

/**
 * LoginDB class validates login information and updates user status to logged-in in the Data base in case the details are correct.
 * @author shays
 *
 */

public class LoginDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.users WHERE userName='" + request.getList().get(0) + "'");
    		
    		if (!result.next())
    			return Result.WRONG_USER;
    		
    		String password = result.getString(2);
    		int status = result.getInt(3);
    		String privilege = result.getString(4);
    		
    		if (password.equals(request.getList().get(1)) == false)
    			return Result.WRONG_PASSWORD;
    		
    		if (status == 1)
    			return Result.ALREADY_LOGIN;
    		
			  stmnt.executeUpdate("UPDATE ghealth.users SET userStatus=0 WHERE username='"+request.getList().get(0)+"'");
			  result.close();

			  
			  return privilege;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
    	
   }

