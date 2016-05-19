package server.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class GetClientIdByClientIdDB {

	public static Object handleMessage(Request request, Connection connection) {
		String getClientDetails = "SELECT * FROM ghealth.person WHERE  ghealth.person.personID = ?";
		ArrayList<String> list = new ArrayList<String>();
		
		try{

    		PreparedStatement statement = connection.prepareStatement(getClientDetails);    				
    		ResultSet res;	
    		
    		statement.setString(1,request.getList().get(0));
    		res = statement.executeQuery();
    		
    		if(!res.next())
    			return Result.ERROR;

    		list.add(Integer.toString(res.getInt(1)));
    		list.add(res.getString(2));
    		list.add(res.getString(3));
    		list.add(res.getString(4));
    		list.add(res.getString(5));
    		list.add(res.getString(6));
    
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return list;
		
	}
	
}
