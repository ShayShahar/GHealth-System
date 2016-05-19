package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.entity.Request;
import common.enums.Result;

public class GetExaminationTypeDB {

	public static Object handleMessage(Request request, Connection connection) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		String stmnt = "SELECT ghealth.examinationtype.typeName FROM ghealth.examinationtype";
	   	try{

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;	
    		
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


