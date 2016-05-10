package server.control.database;

import java.sql.*;

import common.entity.Request;
import common.enums.Result;

public class LogoutDB {

    public static Object handleMessage (Request request, Connection connection) {

			Statement stmt;
			try {
				stmt = connection.createStatement();
				stmt.executeUpdate("UPDATE ghealth.users SET userStatus=1 WHERE userName='" + request.getList().get(0) + "'");
				
				return Result.LOGGEDOUT;
	
			} catch (SQLException e1) {
				e1.printStackTrace();
				return Result.ERROR;
			}
	    	
    }

	
}
