package server.control.database;

import java.sql.*;

import common.entity.Request;
import common.enums.Result;

public class LoginDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.users WHERE username=" + request.getList().get(0));
    		
    		if (!result.next())
    			return Result.WRONG_USER;
    		
    		String password = result.getString(2);
    		int status = result.getInt(3);
    		
    		if (password != request.getList().get(1))
    			return Result.WRONG_PASSWORD;
    		
    		if (status == 1)
    			return Result.ALREADY_LOGIN;
    		
			  stmnt.executeUpdate("UPDATE ghealth.users SET status=1 WHERE username='"+request.getList().get(0)+"'");
			  result.close();
			 
			  return Result.OK;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
    	
   }

