package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class GetBranchesDB {

	public static Object handleMessage (Request request, Connection connection) {
		
	    		String searchBranches = "SELECT ghealth.branches.branchName FROM ghealth.branches";
	    		
	    			try{
	    	    		PreparedStatement statement = connection.prepareStatement(searchBranches);    				
	    	    		ResultSet res;	
	    	    		res = statement.executeQuery();
	    	    		ArrayList<String> list = new ArrayList<String>();
	    	    		
	    	    		while(res.next()){
	    	    			list.add(res.getString(1));
	    	    		}
	    	    
	    				  return list;
	    	    	} catch (SQLException e) {
	    						e.printStackTrace();
	    						return Result.ERROR;
	    				}
	 	    }
}
