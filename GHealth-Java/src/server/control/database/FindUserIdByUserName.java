package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindUserIdByUserName {

	public static Object handleMessage(Request request, Connection connection) {
		int id;
		ResultSet result;
		
	   	try{
    		Statement stmnt = connection.createStatement();
    		 result = stmnt.executeQuery("SELECT * FROM ghealth.users,ghealth.specialists WHERE userName ='" +(String) request.getEntity() +"' AND ghealth.users.personID=ghealth.specialists.personID");
    		 if(!result.next())
    			return Result.ERROR;
    			
    		id = result.getInt(6);
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return id;
	}
	
	

}
