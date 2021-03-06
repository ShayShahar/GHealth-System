package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

/**
 * The Class GetClientIdByClientIdDB returns client's details by person's id.
 */
public class GetClientIdByClientIdDB {


	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
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
