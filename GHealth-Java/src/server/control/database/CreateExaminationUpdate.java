package server.control.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

public class CreateExaminationUpdate {

	public static Object handleMessage(Request request, Connection connection) {
	
		
		Examination exam = new Examination();
		exam = (Examination)request.getEntity();
		
		try {
			Statement stmnt = connection.createStatement();
		    stmnt.executeUpdate("UPDATE `ghealth`.`examination` SET `exDetails`='"+exam.getDetails()+"' WHERE `exID`='"+exam.getId()+"';");
		    
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
