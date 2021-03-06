package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * GetBranchByUserNameDB class search for a branch name by a manager's user name in the Data base.
 *
 * @author shays
 */

public class GetBranchByUserNameDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	public static Object handleMessage (Request request, Connection connection) {
		
		String getBranch = "SELECT branches.branchName "+
				"FROM branches, users " +
				"WHERE users.userName=? AND users.personID=branches.manager";
	
			try{
				
				   PreparedStatement stmnt = connection.prepareStatement(getBranch);    				
				   stmnt.setString(1,request.getList().get(0));
				   ResultSet rs = stmnt.executeQuery();
				   
				   if(!rs.next()){
					   return Result.ERROR;
				   }
				   
				   return rs.getString(1);
				   
		}catch(Exception e){
			e.printStackTrace();
			return Result.ERROR;
		}
	}
	
	
	
}
