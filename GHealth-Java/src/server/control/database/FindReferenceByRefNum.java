package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.entity.Reference;
import common.entity.Request;
import common.enums.Result;

public class FindReferenceByRefNum {
	
	 public static Object handleMessage (Request request, Connection connection)
	 {
		 
		 Reference reference1 = new Reference();
		 String refNum = (String)request.getEntity();
		
		 try {
				Statement stmnt = connection.createStatement();
				ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.reference,ghealth.clients,ghealth.specialists,ghealth.examinationtype WHERE reference.refID ="+Integer.parseInt(refNum));
			   
				
			    if (res.next()) {
			    	 
			    	//get details from the reference table
			    	 reference1.setCId(res.getInt(11));
					    reference1.setSId(res.getInt(16));
					    reference1.setDate(res.getDate(2));
					    reference1.setComments(res.getString(3));
					    if(res.getString(8) == null)
					    	reference1.setCode(0);
					    reference1.setCode(res.getInt(8));
					    reference1.setUrgency(res.getString(4));
					    reference1.setStatus(res.getInt(5));
					    reference1.setRefNum(res.getInt(1));
					    reference1.setType(res.getString(19));
					
			     }
			    
			    else return Result.CLIENT_NOT_FOUND;
			   
		
	 }
		 
		 catch (SQLException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			    return Result.ERROR;
			}
		 
		 return reference1;
    
  }
	
}
