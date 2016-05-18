package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import common.entity.Request;
import common.enums.Result;

public class GetBranchByUserNameDB {

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
				   
				   System.out.println(rs.getString(1));
				   return rs.getString(1);
				   
		}catch(Exception e){
			e.printStackTrace();
			return Result.ERROR;
		}
	}
	
	
	
}
