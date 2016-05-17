package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class MonthlyReportDB {

	
	public static Object handleMessage (Request request, Connection connection) {
		
		String firstWeekOfMonth = "SELECT weekofyear(?)";
		
		String getMonthlyClients = "SELECT COUNT(*), WEEK(appointments.appDate) AS WEEK " +
                               "FROM appointments, specialists " +
                               "WHERE appointments.specialist=specialists.specialistID AND specialists.branchName=? " +
                               "AND appointments.appMissed=0 AND MONTH(appointments.appDate)=? AND YEAR(appointments.appDate)=?" +
                               "GROUP BY WEEK(appointments.appDate) " +
                               "ORDER BY  WEEK(appointments.appDate) ASC";
		
		String getMonthlyWaitingTime = "SELECT SUM(DATEDIFF(appointments.appDate, appointments.appInviteDate)) AS Waiting, WEEK(appointments.appDate) AS WEEK " +
                                   "FROM appointments, specialists " +
                                   "WHERE appointments.specialist=specialists.specialistID AND specialists.branchName=?" +
                                   "AND appointments.appMissed=0 AND " +
                                   "MONTH(appointments.appDate)=? AND YEAR(appointments.appDate)=? " +
                                   "GROUP BY WEEK(appointments.appDate) " +
                                   "ORDER BY WEEK(appointments.appDate) ASC";
		
		boolean flag1 = false; boolean flag2 = false;
		
		try{
			//prepare the first week of the month -> necessary for barChart
			PreparedStatement stmnt1 = connection.prepareStatement(firstWeekOfMonth);
			String strDate = request.getList().get(1) +"-0"+request.getList().get(2)+"-01";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date date = sdf.parse(strDate);
     java.sql.Date sqlDate = new java.sql.Date(date.getTime());
     stmnt1.setDate(1, sqlDate);
			ResultSet week_number = stmnt1.executeQuery();
			int first_week = week_number.getInt(1);
			
			PreparedStatement stmnt2 = connection.prepareStatement(getMonthlyClients);
			stmnt2.setString(1,request.getList().get(0));
			stmnt2.setInt(2,Integer.parseInt(request.getList().get(1)));
			stmnt2.setInt(3,Integer.parseInt(request.getList().get(2)));
			ResultSet res = stmnt2.executeQuery();
			
			ArrayList<Integer> total_clients = new ArrayList<Integer>();
			while(res.next()){
				flag1 = true;
				while(first_week < res.getInt(2)){
					total_clients.add(0);
					first_week++;
				}
				total_clients.add(res.getInt(1));
			}
			
			if (flag1 == false){
				for (int i = 0; i<5; i++){
					total_clients.add(0);
				}
			}
			
			PreparedStatement stmnt3 = connection.prepareStatement(getMonthlyWaitingTime);
			stmnt3.setString(1,request.getList().get(0));
			stmnt3.setInt(2,Integer.parseInt(request.getList().get(1)));
			stmnt3.setInt(3,Integer.parseInt(request.getList().get(2)));
			ResultSet res2 = stmnt3.executeQuery();
			
			ArrayList<Integer> total_waiting = new ArrayList<Integer>();
			first_week = week_number.getInt(1);
			
			while(res2.next()){
				flag2 = true;
				while(first_week < res2.getInt(2)){
					total_waiting.add(0);
					first_week++;
				}
				total_waiting.add(res2.getInt(1));
			}
			
			if (flag2 == false){
				for (int i = 0; i<5; i++){
					total_waiting.add(0);
				}
			}
			
			if (!flag1 && !flag2){
				return Result.FAILED;
			}
			
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(total_clients);
			list.add(total_waiting);

			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			return Result.ERROR;
		}
		
		
		
 }
	
	
}
