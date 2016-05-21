package server.control.database;

import java.sql.*;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

/**
 * FindClientDB class search for client details in the Data base
 * @author shays
 *
 */

public class FindClientDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.clients, ghealth.person WHERE clients.person='" + request.getList().get(0) + "' AND ghealth.person.personID=clients.person");

    		if (!result.next()){
    			return Result.CLIENT_NOT_FOUND;
    		}
    		
    		ArrayList<String> list = new ArrayList<String>();
    		    		
    		list.add(Integer.toString(result.getInt(1)));
    		list.add(result.getString(3));
    		list.add(Integer.toString(result.getInt(4)));
    		list.add((result.getDate(5)).toString());
    		list.add(result.getString(8));
    		list.add(result.getString(9));
    		list.add(result.getString(10));
      	list.add(result.getString(11));
      	list.add(result.getString(12));
      	
    		return list;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
    	
   }

