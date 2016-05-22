package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class GetExaminatioNumberDB {
	
	public static Object handleMessage(Request request, Connection connection) {
		ArrayList<Integer> list = new ArrayList<>();
		
		String findReferenceNumber = "SELECT ghealth.examinationtype.typeID FROM ghealth.examinationtype WHERE ghealth.examinationtype.typeName = ? ";
		String findExaminationNumber = "SELECT ghealth.reference.examination_id FROM ghealth.reference WHERE ghealth.reference.refID = ? ";
	   	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(findReferenceNumber); 
    		PreparedStatement preparedStatement2 = connection.prepareStatement(findExaminationNumber); 
    		ResultSet result,result2;	
    		preparedStatement1.setString(1,request.getList().get(0));
    		preparedStatement2.setString(1,request.getList().get(1));
    		
    		result = preparedStatement1.executeQuery();
    		if(!result.next())
    			return Result.ERROR;
    			
    		list.add(result.getInt(1));
    		
    		result2 = preparedStatement2.executeQuery();
    		if(!result2.next())
    			return Result.ERROR;
    			
    		list.add(result2.getInt(1));
    
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
			}
		return list;
	}
	
	

}



