package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class GetExaminationTypeDB search for the examination types that available in db.
 */
public class GetExaminationTypeDB {


	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	public static Object handleMessage(Request request, Connection connection) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		String stmnt = "SELECT ghealth.examinationtype.typeName FROM ghealth.examinationtype";
		String searchSpecialistId = "SELECT ghealth.specialists.specialistID FROM ghealth.users , ghealth.specialists WHERE userName =? AND ghealth.users.personID=ghealth.specialists.personID";

		try{
			

    		PreparedStatement preparedStatement2 = connection.prepareStatement(searchSpecialistId);    				
    		ResultSet result1;
    		
    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;	
    		
    		
    		preparedStatement2.setString(1,request.getList().get(0));
    		result1 = preparedStatement2.executeQuery();
    		
    		
    		if(!result1.next()){

    			return Result.ERROR;
    		}
    		
    		
    			list.add(Integer.toString(result1.getInt(1)));
    	

    	
    		result = preparedStatement1.executeQuery();
    		if(!result.next()){

    			return Result.ERROR;
    		}
    		
    		do{
    			list.add(result.getString(1));
    		   }while(result.next());


    		return list;
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
		}
	}		
}


