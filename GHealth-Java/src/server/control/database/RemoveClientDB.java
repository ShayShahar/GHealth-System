package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

public class RemoveClientDB {
	
    public static Object handleMessage (Request request, Connection connection) {
    		
		String updateStatus = "UPDATE ghealth.clients SET ghealth.clients.clientStatus=0, ghealth.clients.leftDate=NOW() WHERE ghealth.clients.clientID=?";
		
		try {
		    	
		    	PreparedStatement preparedStatement = connection.prepareStatement(updateStatus);
		    	preparedStatement.setInt(1,Integer.parseInt(request.getList().get(0)));
			    preparedStatement.executeUpdate();

			    return Result.OK;
			   
	
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return Result.ERROR;
		}
	}
	

}
