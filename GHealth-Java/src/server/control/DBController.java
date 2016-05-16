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
			
				case FIND_REFERENCE_BY_SID_CID_DATE:{
					return FindReferenceByCidSidDateDB.handleMessage(request, connection) ;
				}

				case CANCEL_APPOINTMENT:{
					return CancelAppointmentDB.handleMessage(request, connection) ;
				}
				case FIND_REFERENCE_BY_REFNUM:{
					return FindReferenceByRefNum.handleMessage(request, connection);
				}
				case REMOVE_CLIENT:{
					return RemoveClientDB.handleMessage(request, connection);
				}			
				case RETURN_CLIENT:{
					return ReturnClientDB.handleMessage(request, connection);
				}
				case FIND_USERID_BY_USERNAME:{
					return FindUserIdByUserName.handleMessage(request, connection);
				}
				case FIND_TODAY_APPOINTMENT:{
					return FindTodayAppointmentDB.handleMessage(request, connection);
				}
		  	case CREATE_EXAMINATION:{
					return CreateExamination.handleMessage(request, connection);
				}
				case CREATE_EXAMINATION_VIEW:{
					return CreateExaminationView.handleMessage(request, connection);
				}
				case CREATE_EXAMINATION_UPDATE:{
					return CreateExaminationUpdate.handleMessage(request, connection);
				}
				case GET_CLIENT_BY_APPOINTMET:{
					return GetClientByAppointmentDB.handleMessage(request, connection);
				}
				case GET_BRANCHES:{
					return GetBranchesDB.handleMessage(request, connection);
				}
				case WEEKLY_REPORT:{
					return WeeklyReportDB.handleMessage(request, connection);
				}

		default:
			break;
				
		}
		
		return Result.ERROR;
		
	}
	
}
