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

// TODO: Auto-generated Javadoc
/**
 * CreateExaminationDB class creates a new examination to an opened reference in the Data base.
 * @author shays
 *
 */

public class CreateExaminationDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */

	public static Object handleMessage(Request request, Connection connection) {
		
		ArrayList<byte[]> pictures;
		int id;
		Examination exam = new Examination();
		exam = (Examination)request.getEntity();
		pictures = exam.getPictures();
		int num = pictures.size();
		
		String searchExamination = "SELECT * FROM ghealth.reference WHERE ghealth.reference.refID=? AND ghealth.reference.refStatus = 1";
		String insertNewExamination = "INSERT INTO ghealth.examination (exDetails,exPicture0,exPicture1,exPicture2,exPicture3) VALUES (?,?,?,?,?)";
		
		
		try {
			
			/**
			 * check if there is no examination for the reference
			 */
			 PreparedStatement preparedStatement0 = connection.prepareStatement(searchExamination);
			 preparedStatement0.setInt(1, exam.getReferenceId());
			 ResultSet res0 = preparedStatement0.executeQuery();
			 
			 if (res0.next()){
				 return Result.FAILED;
			 }

			 Statement stmnt = connection.createStatement();
			
			 //create new Examination
			
			 PreparedStatement preparedStatement = connection.prepareStatement(insertNewExamination);
			 
			 //set details
			 preparedStatement.setString(1, exam.getDetails());
			 
			 //set pictures
			 for(int i=0;i<num;i++)
		     preparedStatement.setBytes(i+2,exam.getPictures().get(i));
			 
			 
			 for(int j=4;j>num;j--){
			    preparedStatement.setNull(j+1, java.sql.Types.BLOB);   
			 }
				    
			 
			 preparedStatement.executeUpdate();
		    
		    
		   
		   
			 //Create new Exam id to the match reference and change the status to 1
		    
		    ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.examination WHERE ghealth.examination.exDetails ='"+exam.getDetails()+"'");
		   
		    if (res.next()) {
		    	id = res.getInt(1);
		    	stmnt.executeUpdate("UPDATE ghealth.reference SET ghealth.reference.examination_id ='"+id+"',ghealth.reference.refStatus ='1'  WHERE `refID`='"+exam.getReferenceId() +"';");
		    	return id;	
		     }
		    
		 
		    	return Result.ERROR;
		    
		
	}
		
		 catch (SQLException e) {
			    e.printStackTrace();
			    return Result.ERROR;
			}
		
		

}
	
	
}
