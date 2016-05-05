package server.control.database;

import java.sql.*;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindClientDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.clients, ghealth.person WHERE clients.personID='" + request.getList().get(0) + "' AND ghealth.person.id=clients.personID");

    		if (!result.next()){
    			return Result.CLIENT_NOT_FOUND;
    		}
    		
    		ArrayList<String> list = new ArrayList<String>();
    		    		
    		list.add(Integer.toString(result.getInt(1)));
    		list.add(Integer.toString(result.getInt(2)));
    		list.add(result.getString(3));
    		list.add(result.getString(4));
    		list.add(result.getString(5));
    		list.add(result.getString(6));
    		list.add(result.getString(7));

    		return list;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
    	
   }

