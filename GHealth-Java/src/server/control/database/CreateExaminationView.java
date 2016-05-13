package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

public class CreateExaminationView {

	public static Object handleMessage(Request request, Connection connection) {
		
		Examination exam = new Examination();
		Examination exam1 = new Examination();
		exam = (Examination)request.getEntity();
		
		try {
			Statement stmnt = connection.createStatement();
		    ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.examination WHERE ghealth.examination.exID ='"+exam.getId()+"'");
		   
		
		    
		    
		    if (res.next()) {
		    	exam1.setId(res.getInt(1));
		    	exam1.setDetails(res.getString(2));
		     }
		    else return Result.ERROR;
		 
		    
		    
		
	}
	    	
		 catch (SQLException e) {
			    // TODO Auto-generated catch block
			 System.out.println("FindReferenceByCidSidDate Error");
			    e.printStackTrace();
			    return Result.ERROR;
			}
		
		return exam1;
	}

}
