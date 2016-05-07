package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import client.entity.DateChecker;
import common.entity.Request;
import common.enums.Result;

public class FindDatesDB {
	
	static String mask = "111111111111111111";
	
    public static Object handleMessage (Request request, Connection connection) {
    	
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
