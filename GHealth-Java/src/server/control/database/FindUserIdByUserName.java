package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.entity.Request;
import common.enums.Result;

public class FindUserIdByUserName {
//12
	public static Object handleMessage(Request request, Connection connection) {
		int id;
		ResultSet result;
		
	   	try{
    		Statement stmnt = connection.createStatement();
    		System.out.println(request.getList().get(0));
    		result = stmnt.executeQuery("SELECT * FROM ghealth.users , ghealth.specialists WHERE userName ='" + request.getList().get(0) + "' AND ghealth.users.personID=ghealth.specialists.personID");
    		if(!result.next())
    			return Result.ERROR;
    			
    		id = result.getInt(6);
    		System.out.println(id);
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return id;
	}
	
	

}
