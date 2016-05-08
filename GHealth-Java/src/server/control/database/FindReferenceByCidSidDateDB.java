package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.entity.Client;
import client.entity.Reference;
import common.entity.Request;
import common.enums.Result;

public class FindReferenceByCidSidDateDB {
	
	 public static Object handleMessage (Request request, Connection connection) {
	 
		
		 ResultSet res = null;
		 String SearchReference = "SELECT * FROM ghealth.reference WHERE cid = ? && sid = ? && date > ? ";
		 Reference reference1 = new Reference();
			Reference reference = (Reference)request.getEntity();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			
			
			java.sql.Date date = new java.sql.Date(reference.getDate().getTime());                   
			
			System.out.println(date);
			
			try {
				
				PreparedStatement preparedStatement1 = connection.prepareStatement(SearchReference);
			    preparedStatement1.setInt(1,reference.getCId());
			    preparedStatement1.setInt(2,reference.getSId());
			    preparedStatement1.setDate(3,date);
			    res = preparedStatement1.executeQuery();
			    if (res.next()) {
			    	
			    	 reference1.setCId(res.getInt(1));
					    reference1.setSId(res.getInt(2));
					    reference1.setDate(res.getDate(3));
					    reference1.setComments(res.getString(4));
					    reference1.setCode(res.getInt(5));
					    //reference1.setUrgency(res.getString(6));
					   // System.out.println(reference1.getComments());    
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
