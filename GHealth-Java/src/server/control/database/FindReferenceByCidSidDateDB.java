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
			System.out.println("here i'm");
			
			java.sql.Date sqlDate = new java.sql.Date(reference.getDate().getTime()); //convert to sql date  
			
			
			try {
				Statement stmnt = connection.createStatement();
				ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.reference,ghealth.clients,ghealth.specialists,ghealth.examinationtype WHERE reference.client_id = '"+reference.getCId()+"' && reference.specialist_id = '"+reference.getSId()+"' && reference.refDate ='"+sqlDate+"' AND ghealth.reference.client_id = ghealth.clients.clientID AND ghealth.reference.specialist_id = ghealth.specialists.specialistID ");
			   
				
			    if (res.next()) {
			    	
			    	
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
			    
			   else 
			    	return Result.CLIENT_NOT_FOUND;
			   
			    
			    
			    
			    
			}
			
			 catch (SQLException e) {
				    // TODO Auto-generated catch block
				 System.out.println("FindReferenceByCidSidDate Error");
				    e.printStackTrace();
				    return Result.ERROR;
				}
			return reference1;
	 }

}
