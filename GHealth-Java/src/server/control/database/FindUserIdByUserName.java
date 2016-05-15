package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.entity.Request;
import common.enums.Result;

public class FindUserIdByUserName {

	public static Object handleMessage(Request request, Connection connection) {
		int id;
		
		String stmnt = "SELECT ghealth.specialists.specialistID FROM ghealth.users , ghealth.specialists WHERE userName =? AND ghealth.users.personID=ghealth.specialists.personID";
	   	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;	
    		preparedStatement1.setString(1,request.getList().get(0));
    		result = preparedStatement1.executeQuery();
    		if(!result.next())
    			return Result.ERROR;
    			
    		id = result.getInt(1);
    
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return id;
	}
	
	

}
