package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;

/**
 * FindClientExaminationDB class search for all client's examinations in db.
 * @author shays
 */

public class FindClientExaminationDB {
	
	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	   public static Object handleMessage (Request request, Connection connection) {

		  String searchExamination =
				  														"SELECT ghealth.reference.refDate, ghealth.examinationtype.typeName ,ghealth.specialists.specialistType "
				  														+ " ,ghealth.person.personName ,ghealth.person.personFamily,ghealth.reference.examination_id " +
				  														"FROM ghealth.reference ,ghealth.specialists ,ghealth.person ,ghealth.examinationtype " +
				  														"WHERE ghealth.reference.client_id = ? AND ghealth.reference.specialist_id = ghealth.specialists.specialistID "
				  														+ "AND ghealth.specialists.personID = ghealth.person.personID AND "
				  														+ "ghealth.reference.type_id = ghealth.examinationtype.typeID AND ghealth.reference.examination_id > 0 " +
				  														"ORDER BY ghealth.reference.refDate ASC";
		 
		  String searchClientId = "SELECT ghealth.clients.clientID FROM ghealth.clients WHERE ghealth.clients.person = ?";

		   ArrayList<Examination> list = new ArrayList<Examination>();
		   int clientId;
		   
		   	try{
			    PreparedStatement preparedStatement1 = connection.prepareStatement(searchExamination);
			    PreparedStatement preparedStatement2 = connection.prepareStatement(searchClientId);
			    ResultSet res,res2;
			    
			    
			 
			    preparedStatement2.setString(1,request.getList().get(0));
			    res2 = preparedStatement2.executeQuery();
			    
			    if (!res2.next()){
			    	
			    	return Result.ERROR;
			    }
			    
			    clientId = res2.getInt(1);
			    
			    preparedStatement1.setInt(1,clientId);
			    res = preparedStatement1.executeQuery();
			    
			    
			    if (!res.next()){
			 
			    	return Result.ERROR;
			    }
			    
			    do {
			    		Examination temp = new Examination();
			    		String dateInString = res.getString(1);
						String[] date = dateInString.split("-");
						String setDate = date[2]+"-"+date[1]+"-"+date[0];
			    		temp.setDate(setDate);
			    		temp.setExaminationName(res.getString(2));
			    		temp.setSpecialist(res.getString(3));
			    		temp.setSpecialistName(res.getString(4) + " " +res.getString(5));
			    		temp.setSerial(Integer.toString(res.getInt(6)));
			    		list.add(temp);
			    		
			    }while(res.next());
			    
			    return list;
				  
	    	} catch (SQLException e) {
						e.printStackTrace();
						return Result.ERROR;
				}
	    }
	    	
	   }

