package server.control.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.entity.Request;
import common.enums.Result;

public class InsertLabReferenceDB {

	public static Object handleMessage(Request request, Connection connection) {
		
		Date date = new Date(0);
		int exId;
		String insertReference = "INSERT INTO ghealth.reference (refDate, refComments, refUrgency, client_id, specialist_id, "
				+ "type_id) VALUES (?,?,?,?,?,?)";
		
		String stmnt = "SELECT ghealth.examinationtype.typeID FROM ghealth.examinationtype WHERE ghealth.examinationtype.typeName=?";
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
		
		return null;
	}

}
