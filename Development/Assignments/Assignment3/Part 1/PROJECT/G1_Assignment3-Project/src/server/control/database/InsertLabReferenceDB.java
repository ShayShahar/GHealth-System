package server.control.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class InsertLabReferenceDB.
 */
public class InsertLabReferenceDB {

	/**
	 * Handle message.
	 *
	 * @param request the request
	 * @param connection the connection
	 * @return the object
	 */
	public static Object handleMessage(Request request, Connection connection) {
		ArrayList<Integer> list = new ArrayList<>();
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		int exId;
		String insertReference = "INSERT INTO ghealth.reference (refDate, refComments, refUrgency, client_id, specialist_id, "
				+ "type_id) VALUES (?,?,?,?,?,?)";
		
		String stmnt = "SELECT ghealth.examinationtype.typeID FROM ghealth.examinationtype WHERE ghealth.examinationtype.typeName=?";
		String returnReferenceNum = "SELECT ghealth.reference.refID FROM ghealth.reference WHERE ghealth.reference.client_id=? "
				+ "AND ghealth.reference.specialist_id = ? AND ghealth.reference.refDate = ? ";

	   	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;	
    		preparedStatement1.setString(1,request.getList().get(2));
    		result = preparedStatement1.executeQuery();
    		if(!result.next())
    			return Result.ERROR;
    		
    		exId = result.getInt(1);
    		
		PreparedStatement preparedStatement2 = connection.prepareStatement(insertReference);
		preparedStatement2.setDate(1,date);
		preparedStatement2.setString(2,request.getList().get(0));
		preparedStatement2.setString(3,request.getList().get(1));
		preparedStatement2.setInt(6,exId);
		preparedStatement2.setString(4,request.getList().get(3));
		preparedStatement2.setInt(5,Integer.parseInt(request.getList().get(4)));

		preparedStatement2.executeUpdate();

		
    
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
	   	
	   	try{
	   	PreparedStatement preparedStatement3 = connection.prepareStatement(returnReferenceNum);
	   	ResultSet result3 ;
	   	preparedStatement3.setString(1,request.getList().get(3));
		preparedStatement3.setString(2,request.getList().get(4));
		preparedStatement3.setDate(3,date);
		
		result3 = preparedStatement3.executeQuery();
		
		if(!result3.next())
			return Result.ERROR;
		
		do{
			list.add(0, result3.getInt(1));
		  }
			while(result3.next());
		
	   		}
	   	catch (SQLException e) {
			e.printStackTrace();
			return Result.ERROR;
	}
		
		return list;
	}

}
