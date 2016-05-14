package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

public class CreateExaminationUpdate {

	public static Object handleMessage(Request request, Connection connection) {
	
		String updateExam1 ="UPDATE ghealth.examination SET exDetails=?";
		String updateExam2 =  "WHERE exID=?";
		Examination exam = new Examination();
		exam = (Examination)request.getEntity();
		
		try {
			//Statement stmnt = connection.createStatement();
		   // stmnt.executeUpdate("UPDATE `ghealth`.`examination` SET `exDetails`='"+exam.getDetails()+"' WHERE `exID`='"+exam.getId()+"';");
		    int num = exam.getPictures().size(); 
		   
		    for(int i=0 ;i<num;i++)
		    	updateExam1 = updateExam1+",exPicture"+i;
		    
		    updateExam1 = updateExam1+"WHERE exID=?";
		   
		    PreparedStatement preparedStatement = connection.prepareStatement(updateExam1);
		    preparedStatement.setString(1,exam.getDetails());
		    preparedStatement.setInt(2,exam.getId());
		  
		    preparedStatement.executeUpdate();
		    
		         
		 
		    
		}
	    
		

 catch (SQLException e) {
	    // TODO Auto-generated catch block
	 System.out.println("FindReferenceByCidSidDate Error");
	    e.printStackTrace();
	    return Result.ERROR;
	}

		return null;
	}

}
