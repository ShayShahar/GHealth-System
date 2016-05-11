package server.control.database;

import java.sql.*;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class FindSpecialistDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
    	ResultSet result = null;
    	
    	try{
    	
    		String searchAppointments = "SELECT * FROM ghealth.appointments, ghealth.specialists, ghealth.person, ghealth.branches " +
    		                            "WHERE ghealth.appointments.client=? AND appDate < NOW() AND " +
    		                            "ghealth.appointments.specialist=ghealth.specialists.specialistID " +
    	                           		"AND ghealth.specialists.specialistType=? AND ghealth.person.personID=ghealth.specialists.personID " +
                                    "AND ghealth.specialists.branchName=ghealth.branches.branchName " +
                                    "ORDER BY appDate DESC";
    		
    		String findSpecialists = "SELECT * FROM ghealth.specialists," +
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
		    			if (specialists.get(i) == result.getInt(8)){
		    				flag = true;
		    			}
		    	}
		    	if (flag == false){
			    	ArrayList<String> list = new ArrayList<String>();
			    	list.add((Integer.toString(result.getInt(8))));
			    	list.add(result.getString(15));
			    	list.add(result.getString(16));
			    	list.add(result.getString(20));
			    	list.add(result.getString(21));
			    	specialists.add(result.getInt(8));
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
		    
		    if (!result.next() && specialists.size() == 0){
    			return Result.SPECIALIST_NOT_FOUND;
		    }
		    
		    do{
		    	
    			
	    		ArrayList<String> list = new ArrayList<String>();
	    		    		
	    		list.add(Integer.toString(result.getInt(1)));
	    		list.add(result.getString(6));
	    		list.add(result.getString(7));
	      	list.add(result.getString(11));
	      	list.add(result.getString(12));
	      	
	      	objectList.add(list);
		    	
		    }while(result.next());
		    
		    
		    return objectList;
		    
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
			
    }
    	
    	
}
    	
   
