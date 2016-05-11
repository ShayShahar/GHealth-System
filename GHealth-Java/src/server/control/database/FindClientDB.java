package server.control.database;

import java.sql.*;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindClientDB {

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

