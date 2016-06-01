package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import client.entity.Reference;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class FindReferenceByRefNum.
 */
public class FindReferenceByRefNum {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	 public static Object handleMessage (Request request, Connection connection)
	 {
		 
		 Reference reference = new Reference();
		 String refNumber = (String)request.getEntity();
		 
		 String searchReference = "SELECT ghealth.reference.refID, ghealth.reference.refDate, ghealth.reference.refComments, ghealth.reference.refUrgency, " +
		 											"ghealth.reference.refStatus, ghealth.reference.examination_id, ghealth.clients.person, " +
		 											"ghealth.specialists.personID, ghealth.examinationtype.typeName " +
				 									"FROM ghealth.reference,ghealth.clients, ghealth.specialists, ghealth.examinationtype " +
				 									"WHERE reference.refID =? AND ghealth.reference.client_id = ghealth.clients.clientID " +
				 									"AND ghealth.reference.specialist_id = ghealth.specialists.specialistID " +
				 									"AND ghealth.reference.type_id = ghealth.examinationtype.typeID";
		
		 try {
			 
			  PreparedStatement preparedStatement1 = connection.prepareStatement(searchReference);
			  preparedStatement1.setInt(1, Integer.parseInt(refNumber));

			  ResultSet res = preparedStatement1.executeQuery();
				
				
			    if (res.next()) {
			    	 
			    	reference.setCId(res.getInt(7));  
			    	reference.setSId(res.getInt(8));  
			    	reference.setDate(res.getDate(2)); 
			    	reference.setComments(res.getString(3));  
					    if(res.getString(6) == null)    
					    	reference.setCode(0);      
					  reference.setCode(res.getInt(6));     
					  reference.setUrgency(res.getString(4));  
					  reference.setStatus(res.getInt(5)); 
					  reference.setRefNum(res.getInt(1));
					  reference.setType(res.getString(9));
					  
					  return reference;
			    }
			    
			    else return Result.FAILED;
			   
	 }
		 
		 catch (SQLException e) {
			    e.printStackTrace();
			    return Result.ERROR;
			}
  }
	
}
