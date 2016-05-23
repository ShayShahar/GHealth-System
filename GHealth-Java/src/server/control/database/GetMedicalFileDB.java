package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import client.entity.MedicalFile;
import common.entity.Request;
import common.enums.Result;


/**
 * GetMedicalFileDB class search for all medical information of a client in the Data base
 * @author shays
 *
 */

public class GetMedicalFileDB {


	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
    	ResultSet result = null;
    	    	
    	String searchAppointments = "SELECT appointments.appDate, specialists.specialistType, person.personName, person.personFamily, appointments.appID "
    			+  "FROM appointments, specialists, person "
    			+  "WHERE appointments.appDate < CURDATE() AND appointments.client=? AND appointments.specialist = specialists.specialistID AND specialists.personID = person.personID "
    			+  "ORDER BY appointments.appDate";
    	
    	String searchReferences = "SELECT reference.refDate, specialists.specialistType, person.personName, person.personFamily, reference.refID " +
    																"FROM reference, specialists, person " + 
    																"WHERE reference.client_id=? AND reference.specialist_id = specialists.specialistID AND specialists.personID = person.personID "
    															+ "ORDER BY reference.refDate";
    	
    	ArrayList<MedicalFile> list = new ArrayList<MedicalFile>();
    	
    	try{
    			    
		    PreparedStatement preparedStatement = connection.prepareStatement(searchAppointments);
		    preparedStatement.setInt(1,Integer.parseInt(request.getList().get(0)));
    		
		    result = preparedStatement.executeQuery();
		    
		    while (result.next()){
		    	MedicalFile mf = new MedicalFile();
		    	mf.setDate(result.getDate(1).toString());
		    	mf.setSpecialization(result.getString(2));
		    	mf.setName(result.getString(3) + " " + result.getString(4));
		    	mf.setId(result.getString(5));
		    	mf.setType("Appointment");

		    	list.add(mf);
		    }
		    
		    PreparedStatement preparedStatement2 = connection.prepareStatement(searchReferences);
		    preparedStatement2.setInt(1,Integer.parseInt(request.getList().get(0)));
    		
		    result = preparedStatement2.executeQuery();
		    
		    while (result.next()){
		    	MedicalFile mf = new MedicalFile();
		    	mf.setDate(result.getDate(1).toString());
		    	mf.setSpecialization(result.getString(2));
		    	mf.setName(result.getString(3) + " " + result.getString(4));
		    	mf.setId(result.getString(5));
		    	mf.setType("Reference");

		    	list.add(mf);
		    }
		    
		    Collections.sort(list, new Comparator<MedicalFile>() {
		        public int compare(MedicalFile m1, MedicalFile m2) {
		            return m2.getDate().compareTo(m1.getDate());
		        }
		    });		    
		    
		    return list;
		    
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
			
    }
    	
    	
	
}
