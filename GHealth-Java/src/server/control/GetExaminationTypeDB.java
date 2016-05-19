package server.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class GetExaminationTypeDB {

	public static Object handleMessage(Request request, Connection connection) {
		
		ArrayList<String> list = new ArrayList<String>();
		String stmnt = "SELECT ghealth.examinationtype. FROM ghealth.examinationtype , ghealth.specialists WHERE userName =? AND ghealth.users.personID=ghealth.specialists.personID";
	   	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;	
    		preparedStatement1.setString(1,request.getList().get(0));
    		result = preparedStatement1.executeQuery();
    		if(!result.next())
    			return Result.ERROR;
    			
    		list.add(result.getString(1));
    
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return id;
	}		
	}


