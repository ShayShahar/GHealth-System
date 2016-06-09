package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * MonthlyReportDB creates a monthly report from the information that stored in mySQL database
 * Monthly0 report includes number of clients that got treatments, waiting time for appointments (from invite date),
 * clients that left IHealth and missing appointments.
 * All the information grouped by the weeks of the month.
 * @author shays
 *
 */

public class MonthlyReportDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	public static Object handleMessage (Request request, Connection connection) {
		
		String firstWeekOfMonth = "SELECT WEEK(?)";
		
		String getMonthlyClients = "SELECT COUNT(*), WEEK(appointments.appDate) AS WEEK " +
                               "FROM appointments, specialists " +
                               "WHERE appointments.specialist=specialists.specialistID AND specialists.branchName=? " +
                               "AND appointments.appMissed=0 AND MONTH(appointments.appDate)=? AND YEAR(appointments.appDate)=? " +
                               "GROUP BY WEEK(appointments.appDate) " +
                               "ORDER BY  WEEK(appointments.appDate) ASC";
		
		String getMonthlyWaitingTime = "SELECT SUM(DATEDIFF(appointments.appDate, appointments.appInviteDate)) AS Waiting, WEEK(appointments.appDate) AS WEEK " +
                                   "FROM appointments, specialists " +
                                   "WHERE appointments.specialist=specialists.specialistID AND specialists.branchName=? " +
                                   "AND appointments.appMissed=0 AND " +
                                   "MONTH(appointments.appDate)=? AND YEAR(appointments.appDate)=? " +
                                   "GROUP BY WEEK(appointments.appDate) " +
                                   "ORDER BY WEEK(appointments.appDate) ASC";
		
		String getMonthlyMissingAppointments = "SELECT COUNT(*), WEEK(appointments.appDate) AS WEEK " +
																								"FROM appointments, specialists " +
																								"WHERE appointments.specialist=specialists.specialistID AND specialists.branchName=? " +
																								"AND appointments.appMissed=1 AND MONTH(appointments.appDate)= ? AND YEAR(appointments.appDate)=? " +
																								"GROUP BY WEEK(appointments.appDate) " +
																								"ORDER BY  WEEK(appointments.appDate) ASC";
		
		String getMonthlyLeftClients = "SELECT COUNT(*), WEEK(clients.leftDate) AS WEEK " +
																				"FROM clients " +
																				"WHERE clients.clientStatus = 0 " +
																				"AND MONTH(clients.leftDate)=? AND YEAR(clients.leftDate)=? " +
																				"GROUP BY WEEK(clients.leftDate) " +
																				"ORDER BY  WEEK(clients.leftDate) ASC";
		
		boolean flag1 = false; boolean flag2 = false; boolean flag3 = false; boolean flag4 = false;
		
		try{
			//prepare the first week of the month -> necessary for barChart
			PreparedStatement stmnt1 = connection.prepareStatement(firstWeekOfMonth);
			String strDate;
			if (Integer.parseInt(request.getList().get(2)) < 10)
				strDate = request.getList().get(1) +"0"+request.getList().get(2) + "01";
			else{
				strDate = request.getList().get(1) + request.getList().get(2) + "01";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date date = sdf.parse(strDate);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			stmnt1.setDate(1, sqlDate);
			ResultSet week_number = stmnt1.executeQuery();
			week_number.next();
			int first_week = week_number.getInt(1);
			PreparedStatement stmnt2 = connection.prepareStatement(getMonthlyClients);
			stmnt2.setString(1,request.getList().get(0));
			stmnt2.setInt(2,Integer.parseInt(request.getList().get(2)));
			stmnt2.setInt(3,Integer.parseInt(request.getList().get(1)));
			ResultSet res = stmnt2.executeQuery();
			ArrayList<Integer> total_clients = new ArrayList<Integer>();
			
			int count = 0;			
			while(res.next()){
				flag1 = true;
				while(first_week < res.getInt(2)){
					total_clients.add(0);
					first_week++;
					count++;
				}
				total_clients.add(res.getInt(1));
				first_week++;
				count++;
			}
			
			for (int i = count ; i < 5 ; i++){
				total_clients.add(0);
			}
			
			if (flag1 == false){
				for (int i = 0; i<5; i++){
					total_clients.add(0);
				}
			}
			
			PreparedStatement stmnt3 = connection.prepareStatement(getMonthlyWaitingTime);
			stmnt3.setString(1,request.getList().get(0));
			stmnt3.setInt(2,Integer.parseInt(request.getList().get(2)));
			stmnt3.setInt(3,Integer.parseInt(request.getList().get(1)));
			ResultSet res2 = stmnt3.executeQuery();
			
			ArrayList<Integer> total_waiting = new ArrayList<Integer>();
			first_week = week_number.getInt(1);
			
			count = 0;			
			while(res2.next()){
				flag2 = true;
				while(first_week < res2.getInt(2)){
					total_waiting.add(0);
					first_week++;
					count++;
				}
				total_waiting.add(res2.getInt(1));
				first_week++;
				count++;
			}
			
			for (int i = count ; i < 5 ; i++){
				total_waiting.add(0);
			}
			
			if (flag2 == false){
				for (int i = 0; i<5; i++){
					total_waiting.add(0);
				}
			}
					
			
			PreparedStatement stmnt4 = connection.prepareStatement(getMonthlyMissingAppointments);
			stmnt4.setString(1,request.getList().get(0));
			stmnt4.setInt(2,Integer.parseInt(request.getList().get(2)));
			stmnt4.setInt(3,Integer.parseInt(request.getList().get(1)));
			ResultSet res3 = stmnt4.executeQuery();
			
			ArrayList<Integer> total_missed = new ArrayList<Integer>();
			first_week = week_number.getInt(1);
			
			count = 0;			
			while(res3.next()){
				flag3 = true;
				while(first_week < res3.getInt(2)){
					total_missed.add(0);
					first_week++;
					count++;
				}
				total_missed.add(res3.getInt(1));
				first_week++;
				count++;
			}
			
			for (int i = count ; i < 5 ; i++){
				total_missed.add(0);
			}
			
			if (flag3 == false){
				for (int i = 0; i<5; i++){
					total_missed.add(0);
				}
			}
			
			
			
			PreparedStatement stmnt5 = connection.prepareStatement(getMonthlyLeftClients);
			stmnt5.setInt(1,Integer.parseInt(request.getList().get(2)));
			stmnt5.setInt(2,Integer.parseInt(request.getList().get(1)));
			ResultSet res4 = stmnt5.executeQuery();
			
			ArrayList<Integer> total_left = new ArrayList<Integer>();
			first_week = week_number.getInt(1);
			
			
			count = 0;			
			while(res4.next()){
				flag4 = true;
				while(first_week < res4.getInt(2)){
					total_left.add(0);
					first_week++;
					count++;
				}
				total_left.add(res4.getInt(1));
				first_week++;
				count++;
			}
			
			for (int i = count ; i < 5 ; i++){
				total_left.add(0);
			}
			
			if (flag4 == false){
				for (int i = 0; i<5; i++){
					total_left.add(0);
				}
			}
						
			
			if (!flag1 && !flag2 && !flag3 && !flag4){
				return Result.FAILED;
			}
			
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(total_clients);
			list.add(total_waiting);
			list.add(total_missed);
			list.add(total_left);
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			return Result.ERROR;
		}
		
		
		
 }
	
	
}
