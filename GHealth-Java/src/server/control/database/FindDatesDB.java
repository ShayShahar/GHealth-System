package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import client.entity.DateChecker;
import common.entity.Request;
import common.enums.Result;


	// TODO: Auto-generated Javadoc
/**
	 * FindDatesDB class search for blocked dates in the Data base.
	 *
	 * @author shays
	 */

public class FindDatesDB {
	
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
    	String mask = "111111111111111111";

    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.dates WHERE dates.specID='" + request.getList().get(0) + "' AND ghealth.dates.dateDate > NOW()");

    		if (!result.next()){
    			return Result.ALL_AVAILABLE;
    		}
    		
    		ArrayList<DateChecker> list = new ArrayList<DateChecker>();
    		
    		do{
    		
    		/**
    		 * add only dates that full to mark on the calendar
    		 */
    			
    		if (result.getString(3).equals(mask)){
    			
        		DateChecker date = new DateChecker();  
        		date.setDate(result.getDate(1));
    			
        		list.add(date);
    		}
  
    		} while (result.next());
      	
    		return list;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
	
	

}
