package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

public class CreateExamination {

	public static Object handleMessage(Request request, Connection connection) {
		
		int id;
		Examination exam = new Examination();
		exam = (Examination)request.getEntity();
		
		try {
			Statement stmnt = connection.createStatement();
		    stmnt.executeUpdate("INSERT INTO `ghealth`.`examination` (`exDetails`) VALUES ('"+exam.getDetails()+"');");
		    
		    ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.examination WHERE ghealth.examination.exDetails ='"+exam.getDetails()+"'");
		   
		
		    
		    
		    if (res.next()) {
		    	id = res.getInt(1);
		    	
		    	 stmnt.executeUpdate("UPDATE ghealth.reference SET ghealth.reference.examination_id ='"+id+"' WHERE `refID`='"+exam.getRef_id()+"';");
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
