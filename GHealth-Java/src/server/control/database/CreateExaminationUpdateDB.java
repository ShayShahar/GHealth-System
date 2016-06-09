package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * CreateExaminationUpdateDB class updates an existed examination in the Data base.
 * @author shays
 *
 */


public class CreateExaminationUpdateDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */

	public static Object handleMessage(Request request, Connection connection) {  //updateExamination Details
	
		String updateExam1 ="UPDATE ghealth.examination SET exDetails=?";
		Examination exam = new Examination();
		exam = (Examination)request.getEntity();
		
		try {
			
		    int num = exam.getPictures().size();  //take the size of the picture array , so we will know how many pictures to add
		    
		   
		    for(int i=0 ;i<4;i++)
		    	updateExam1 = updateExam1+",exPicture"+i+"=? ";  //building the statement
		    
		    updateExam1 = updateExam1+"WHERE exID=?";   //finishing the statement
		    
		   
		    PreparedStatement preparedStatement = connection.prepareStatement(updateExam1);
		    preparedStatement.setString(1,exam.getDetails());
		    preparedStatement.setInt(6,exam.getId());
		    
		    for(int i=0;i<num;i++) //add the pictures to DB
		    preparedStatement.setBytes(i+2,exam.getPictures().get(i));
		    
		    for(int j=4;j>num;j--)  //set null on the null pictures
		    {
		    preparedStatement.setNull(j+1, java.sql.Types.BLOB);   
		    }
		    
		    preparedStatement.executeUpdate();
		    
		         
		 
		    
		}
	    
		

 catch (SQLException e) {
	    e.printStackTrace();
	    return Result.ERROR;
	}

		return null;
	}

}
