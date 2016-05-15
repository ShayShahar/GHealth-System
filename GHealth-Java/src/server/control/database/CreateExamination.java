package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

public class CreateExamination {

	public static Object handleMessage(Request request, Connection connection) {
		
		ArrayList<byte[]> pictures;
		int id;
		Examination exam = new Examination();
		exam = (Examination)request.getEntity();
		pictures = exam.getPictures();
		int num = pictures.size();
		
		String insertNewExamination = "INSERT INTO ghealth.examination (exDetails,exPicture0,exPicture1,exPicture2,exPicture3) VALUES (?,?,?,?,?)";
		
		try {
			
			Statement stmnt = connection.createStatement();
			
			 PreparedStatement preparedStatement = connection.prepareStatement(insertNewExamination);
			 
			 //set details
			 preparedStatement.setString(1, exam.getDetails());
			 
			 //set pictures
			 for(int i=0;i<num;i++)
		     preparedStatement.setBytes(i+2,exam.getPictures().get(i));
			 
			 
			 for(int j=4;j>num;j--)
			    {
			    preparedStatement.setNull(j+1, java.sql.Types.BLOB);   // java.sql.Types.BLOB
			    System.out.println("Picture Number "+j+" is NULL NOW");
			    }
				    
			 
			 preparedStatement.executeUpdate();
		    
		    
		   
		   
		 //Create new Exam id to the match reference
		    
		    ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.examination WHERE ghealth.examination.exDetails ='"+exam.getDetails()+"'");
		    if (res.next()) {
		    	id = res.getInt(1);
		    	
		    	 stmnt.executeUpdate("UPDATE ghealth.reference SET ghealth.reference.examination_id ='"+id+"',ghealth.reference.refStatus ='1'  WHERE `refID`='"+exam.getRef_id()+"';");
		    	 
		    return id;	
		 
		     }
		    
		 
		    	return Result.ERROR;
		    
		
	}
		
		 catch (SQLException e) {
			    // TODO Auto-generated catch block
			 System.out.println("FindReferenceByCidSidDate Error");
			    e.printStackTrace();
			    return Result.ERROR;
			}
		
		

}
	
	
}
