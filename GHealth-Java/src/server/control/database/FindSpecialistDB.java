package server.control.database;

import java.sql.*;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindSpecialistDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
    	try{
    		
    		Statement stmnt = connection.createStatement();
    		ResultSet result = stmnt.executeQuery("SELECT * FROM ghealth.specialists,"
    				+ " ghealth.person, ghealth.branches WHERE specialists.specialistType='" + request.getList().get(0) + 
    				"' AND ghealth.person.personID=ghealth.specialists.personID AND ghealth.specialists.branchName=ghealth.branches.branchName");

    		if (!result.next()){
    			return Result.SPECIALIST_NOT_FOUND;
    		}
    		
    		ArrayList<Object> resultList = new ArrayList<Object>();
    		
    		do{
    			
	    		ArrayList<String> list = new ArrayList<String>();
	    		    		
	    		list.add(Integer.toString(result.getInt(1)));
	    		list.add(result.getString(2));
	    		list.add(result.getString(6));
	    		list.add(result.getString(7));
	      	list.add(result.getString(11));
	      	list.add(result.getString(12));
	      	
	      	resultList.add(list);
    		} while(result.next());
      	
    		return resultList;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
    }
    	
    	
}
    	
   
