package server.control.database;

import java.sql.*;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;


	// TODO: Auto-generated Javadoc
/**
	 * FindSpecialistDB class search for a specialists from specific specialization in the Data base.
	 *
	 * @author shays
	 */

public class FindSpecialistDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
    public static Object handleMessage (Request request, Connection connection) {
    	
    	ResultSet result = null;
    	boolean not_found = false;
    	try{
    	
    		String searchAppointments = "SELECT ghealth.appointments.specialist, ghealth.person.personName, ghealth.person.personFamily, ghealth.branches.branchName, ghealth.branches.branchAddress FROM ghealth.appointments, ghealth.specialists, ghealth.person, ghealth.branches " +
    		                            "WHERE ghealth.appointments.client=? AND appDate < NOW() AND " +
    		                            "ghealth.appointments.specialist=ghealth.specialists.specialistID " +
    	                           		"AND ghealth.specialists.specialistType=? AND ghealth.person.personID=ghealth.specialists.personID " +
    	                           		"AND ghealth.specialists.branchName=ghealth.branches.branchName " +
    	                           		"ORDER BY appDate DESC";
    		
    		String findSpecialists = "SELECT ghealth.specialists.specialistID, ghealth.person.personName, ghealth.person.personFamily, ghealth.branches.branchName, ghealth.branches.branchAddress FROM ghealth.specialists," +
    		                  		   " ghealth.person, ghealth.branches WHERE specialists.specialistType=?" +
    				                     " AND ghealth.person.personID=ghealth.specialists.personID AND" +
    				                     " ghealth.specialists.branchName=ghealth.branches.branchName";
    		
    		 
		    PreparedStatement preparedStatement1 = connection.prepareStatement(searchAppointments);
		    
		    preparedStatement1.setInt(1, Integer.parseInt(request.getList().get(0)));
		    preparedStatement1.setString(2, request.getList().get(1));
    		
		    result = preparedStatement1.executeQuery();
		    ArrayList<Integer> specialists = new ArrayList<Integer>();
		    ArrayList<Object> objectList = new ArrayList<Object>();
		    while (result.next()){
		    	boolean flag = false;
		    	for (int i = 0; i<specialists.size(); i++){
		    			if (specialists.get(i) == result.getInt(1)){
		    				flag = true;
		    			}
		    	}
		    	if (flag == false){
			    	ArrayList<String> list = new ArrayList<String>();
			    	list.add((Integer.toString(result.getInt(1))));
			    	list.add(result.getString(2));
			    	list.add(result.getString(3));
			    	list.add(result.getString(4));
			    	list.add(result.getString(5));
			    	specialists.add(result.getInt(1));
			    	objectList.add(list);
		    	}
		    }
    		
		    StringBuilder builder = new StringBuilder(findSpecialists);

		    for (int i = 0; i < specialists.size() ; i++){
			    builder.insert(builder.length(), " AND ghealth.specialists.specialistID<>" +specialists.get(i));
		    }

		    PreparedStatement preparedStatement2 = connection.prepareStatement(builder.toString());
		    preparedStatement2.setString(1, request.getList().get(1));
		    result = preparedStatement2.executeQuery();
		    if (!result.next()){
		    		not_found = true;
		    }
		    else{
					    do{
					    	ArrayList<String> list = new ArrayList<String>();
				    		    		
				    		list.add(Integer.toString(result.getInt(1)));
				    		list.add(result.getString(2));
				    		list.add(result.getString(3));
				      	list.add(result.getString(4));
				      	list.add(result.getString(5));
				      	
				      	objectList.add(list);
					    	
					    }while(result.next());
		    }
		    
		    if (not_found == true && specialists.size() == 0){
		    	return Result.SPECIALIST_NOT_FOUND;
		    }
		    return objectList;
		    
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
			
    }
    	
    	
}
    	
   
