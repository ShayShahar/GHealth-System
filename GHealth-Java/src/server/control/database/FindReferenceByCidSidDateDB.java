package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import client.entity.Reference;
import common.entity.Request;
import common.enums.Result;

public class FindReferenceByCidSidDateDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	 public static Object handleMessage (Request request, Connection connection) {
	 
		
		 	Reference reference1 = new Reference();
			Reference reference = (Reference)request.getEntity();
			
			java.sql.Date sqlDate = new java.sql.Date(reference.getDate().getTime()); //convert to sql date  
			
			
			try {
				Statement stmnt = connection.createStatement();
				ResultSet res = stmnt.executeQuery("SELECT ghealth.reference.refID,ghealth.reference.refDate"
						+ ", ghealth.reference.refComments,ghealth.reference.refUrgency, ghealth.reference.refStatus,ghealth.reference.examination_id,ghealth.clients.person,ghealth.specialists.personID,ghealth.examinationtype.typeName "
						+ "FROM ghealth.reference,ghealth.clients,ghealth.specialists,ghealth.examinationtype WHERE reference.client_id = '"+reference.getCId()+"' && reference.specialist_id = '"+reference.getSId()+"' && reference.refDate ='"+sqlDate+"'"
						+ " AND ghealth.reference.client_id = ghealth.clients.clientID AND ghealth.reference.specialist_id = ghealth.specialists.specialistID AND ghealth.reference.type_id = ghealth.examinationtype.typeID ");
			   
				
			    if (res.next()) {
			    	//get details from the reference table
			    		reference1.setCId(res.getInt(7));  
			    		reference1.setSId(res.getInt(8));  
			    		reference1.setDate(res.getDate(2)); 
			    		reference1.setComments(res.getString(3));  
			    		if(res.getString(6) == null)    
								reference1.setCode(0);      
						   	reference1.setCode(res.getInt(6));     
						   	reference1.setUrgency(res.getString(4));  
						   	reference1.setStatus(res.getInt(5));
						   	reference1.setRefNum(res.getInt(1)); 
						   	reference1.setType(res.getString(9));
			     }
			   else 
			    	return Result.CLIENT_NOT_FOUND;
			   
			}
			
			 catch (SQLException e) {
				    e.printStackTrace();
				    return Result.ERROR;
				}
			return reference1;
	 }

}
