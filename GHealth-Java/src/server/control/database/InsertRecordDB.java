package server.control.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.entity.Request;
import common.enums.Result;

public class InsertRecordDB {

	public static Object handleMessage(Request request, Connection connection) {


		String updateAppointment = "UPDATE ghealth.appointment SET ghealth.appointment.appReview=? WHERE ghealth.appointment.appID=?";
		
		try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(updateAppointment);    				
	
    		preparedStatement1.setInt(1,Integer.parseInt(request.getList().get(0)));
    		preparedStatement1.setString(2,request.getList().get(1));
    		preparedStatement1.executeUpdate();

    	
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		
		return null;
	}

}
