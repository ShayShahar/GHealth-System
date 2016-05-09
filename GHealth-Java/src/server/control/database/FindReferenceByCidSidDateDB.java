package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import client.entity.Client;
import client.entity.Reference;
import common.entity.Request;
import common.enums.Result;

public class FindReferenceByCidSidDateDB {
	
	 public static Object handleMessage (Request request, Connection connection) {
	 
		
		    Reference reference1 = new Reference();
			Reference reference = (Reference)request.getEntity();
			
			
			java.sql.Date sqlDate = new java.sql.Date(reference.getDate().getTime()); //convert to sql date  
			
			
			try {
				Statement stmnt = connection.createStatement();
				ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.reference WHERE reference.cid = '"+reference.getCId()+"' && reference.sid = '"+reference.getSId()+"' && reference.date ='"+sqlDate+"' ");
			   
				
			    if (res.next()) {
			    	
			    	//get details from the reference table
			    	 reference1.setCId(res.getInt(1));
					    reference1.setSId(res.getInt(2));
					    reference1.setDate(res.getDate(3));
					    reference1.setComments(res.getString(4));
					    reference1.setCode(res.getInt(5));
					    reference1.setUrgency(res.getString(6));
					    reference1.setStatus(res.getInt(7));
					    reference1.setRefNum(res.getInt(8));
					    
					
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
