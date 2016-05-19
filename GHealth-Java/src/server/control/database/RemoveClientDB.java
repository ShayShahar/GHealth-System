package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

public class RemoveClientDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    		
    String getAppointments= "SELECT ghealth.appointments.appDate, ghealth.appointments.appTime, ghealth.appointments.specialist "
    		+ "FROM ghealth.appointments WHERE ghealth.appointments.client=? AND appointments.appDate>CURDATE()";
    String deleteAppointments = "DELETE FROM ghealth.appointments WHERE ghealth.appointments.client=? AND appointments.appDate>CURDATE()";
	  String searchDate = "SELECT ghealth.dates.appointments FROM ghealth.dates WHERE dateDate=? AND specID=?";
	  String deleteDate = "DELETE FROM ghealth.dates WHERE dateDate=? AND specID=?";
	  String updateDate = "UPDATE ghealth.dates SET appointments=? WHERE dateDate=? AND specID=?";
		String updateStatus = "UPDATE ghealth.clients SET ghealth.clients.clientStatus=0, ghealth.clients.leftDate=NOW() WHERE ghealth.clients.clientID=?";
		
		try {
		    	
			PreparedStatement preparedStatement1 = connection.prepareStatement(getAppointments);
	    preparedStatement1.setInt(1,Integer.parseInt(request.getList().get(0)));
	    ResultSet res1 = preparedStatement1.executeQuery();
	    System.out.println("stmnt1");
		  PreparedStatement preparedStatement2 = connection.prepareStatement(deleteAppointments);
		  preparedStatement2.setInt(1,Integer.parseInt(request.getList().get(0)));
		  preparedStatement2.executeUpdate();
		  System.out.println("stmnt2");

	    while (res1.next()){
	    	
			  PreparedStatement preparedStatement3 = connection.prepareStatement(searchDate);
			    preparedStatement3.setInt(1,Integer.parseInt(request.getList().get(0)));
			    preparedStatement3.setInt(2,res1.getInt(3));
			    
			    ResultSet res2 = preparedStatement3.executeQuery();
			    
			    while (res2.next()){
			    	
			    	StringBuilder builder = new StringBuilder(res2.getString(1));
			    	builder.setCharAt(res1.getInt(2)-1,'0');
			    	
			    	if (builder.toString().equals("000000000000000000")){
			    		PreparedStatement preparedStatement4 = connection.prepareStatement(deleteDate);
			    		preparedStatement4.setDate(1,res1.getDate(1));
			    		preparedStatement4.setInt(2,res1.getInt(3));
			    		preparedStatement4.executeUpdate();
			    	}
			    	else{
						  PreparedStatement preparedStatement4 = connection.prepareStatement(updateDate);
				    		preparedStatement4.setString(1,builder.toString());
				    		preparedStatement4.setDate(2,res1.getDate(1));
				    		preparedStatement4.setInt(3,res1.getInt(3));
				    		preparedStatement4.executeUpdate();
			    	}
			    	
			    }
	    }
			
		    	PreparedStatement preparedStatement5 = connection.prepareStatement(updateStatus);
		    	preparedStatement5.setInt(1,Integer.parseInt(request.getList().get(0)));
			    preparedStatement5.executeUpdate();

			    return Result.OK;
			   
	
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return Result.ERROR;
		}
	}
	

}
