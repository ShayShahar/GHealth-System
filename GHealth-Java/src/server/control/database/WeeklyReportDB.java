package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class WeeklyReportDB {

	public static Object handleMessage (Request request, Connection connection) {
		
		
		 //use dayofweek(appDate) -> 0- Sunday, 1- Monday, Etc...
		
		//get total number of clients from the current week perday
		String dailyClients = "SELECT COUNT(*) AS count from ghealth.appointments, ghealth.specialists " +
				                  "WHERE WEEK(appDate)=? AND YEAR(appDate)=? AND dayofweek(appDate)=? " +
				                  "AND appointments.appMissed=0 AND appointments.specialist=specialists.specialistID " +
				                  "AND specialists.branchName=?";
		
		//get total number of waiting time from the current week perday
		String dailyWaitingTime = "SELECT SUM(DATEDIFF(appointments.appDate, appointments.appInviteDate)) AS waitingTime " +
																	"FROM ghealth.appointments, ghealth.specialists " +
																	"WHERE WEEK(appDate)=? AND YEAR(appDate)=? " +
																	"AND dayofweek(appDate)=? AND appointments.appMissed=0 " +
																	"AND appointments.specialist=specialists.specialistID AND specialists.branchName=?"; 
		
			try{
				ArrayList<Object> list = new ArrayList<Object>();
		    PreparedStatement statement = connection.prepareStatement(dailyClients);    				
			  PreparedStatement statement2 = connection.prepareStatement(dailyWaitingTime);    				
			  ResultSet res = null;
			  ResultSet res2 = null;
				
				for (int i = 0 ; i<5; i++){
					System.out.println(i + " " + request.getList().get(2) + " " + request.getList().get(1));
					ArrayList<Integer> day = new ArrayList<Integer>();
				  statement.setInt(1,Integer.parseInt(request.getList().get(2)));
			    statement.setInt(2,Integer.parseInt(request.getList().get(1)));
		    	statement.setInt(3,i+1);
		    	statement.setString(4,request.getList().get(0));
		    	res = statement.executeQuery();
		    	res.next();
					day.add(res.getInt(1));
					statement2.setInt(1,Integer.parseInt(request.getList().get(2)));
					statement2.setInt(2,Integer.parseInt(request.getList().get(1)));
					statement2.setInt(3,i+1);
					statement2.setString(4,request.getList().get(0));
		    	res2 = statement2.executeQuery();
		    	res2.next();		    	
					day.add(res2.getInt(1));
					list.add(day);
				}

				  return list;
	    	} catch (SQLException e) {
						e.printStackTrace();
						return Result.ERROR;
				}
	    }
	
}
