package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.entity.Request;
import common.enums.Result;


/**
 * FindHoursDB class search for available hours to create appointment in specific date in the Data base
 * @author shays
 *
 */

public class FindHoursDB {
	
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
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.dates WHERE dates.specID='" 
    		+ request.getList().get(0) + "' AND ghealth.dates.dateDate ='"+request.getList().get(1)+"'");

    		if (!result.next()){
    			return Result.ALL_AVAILABLE;
    		}
    		
    		return result.getString(3); 
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }	
}
