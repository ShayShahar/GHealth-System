package server.control;

import java.sql.Connection;
import common.entity.Request;
import server.control.database.*;

public class DBController {

	
	public static Object processRequest(Request request, Connection connection){

		switch (request.getCommand()){
		
		case LOGIN:{
			return LoginDB.handleMessage(request, connection) ;
		}
		
		case LOGOUT:{
			return LogoutDB.handleMessage(request, connection) ;
		}
		
		}
		
		return null;
		
	}
	
}
