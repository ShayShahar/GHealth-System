package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.entity.Request;
import common.enums.Result;

public class FindHoursDB {
	
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
