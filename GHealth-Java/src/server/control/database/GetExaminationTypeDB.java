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
 * The Class GetExaminationTypeDB.
 */
public class GetExaminationTypeDB {

	/**
	 * Handle message.
	 *
	 * @param request the request
	 * @param connection the connection
	 * @return the object
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


