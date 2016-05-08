package server.control;

import java.sql.Connection;
import common.entity.Request;
import common.enums.Result;
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
				
				case FIND_CLIENT:{
					return FindClientDB.handleMessage(request, connection) ;
				}
				
				case CREATE_CLIENT:{
					return CreateClientDB.handleMessage(request, connection) ;
				}
				
				case FIND_SPECIALIST:{
					return FindSpecialistDB.handleMessage(request, connection) ;
				}
				
				case FIND_FULL_DATES:{
					return FindDatesDB.handleMessage(request, connection) ;
				}
				
				case FIND_AVAILABLE_HOURS:{
					return FindHoursDB.handleMessage(request, connection) ;
				}
				
				case CREATE_APPOINTMENT:{
					return CreateAppointmentDB.handleMessage(request, connection) ;
				}
				
				case FIND_APPOINTMENTS:{
					return FindAppointmentsDB.handleMessage(request, connection) ;
				}
		default:
			break;
				
		}
		
		return Result.ERROR;
		
	}
	
}
