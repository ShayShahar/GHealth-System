package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.entity.MedicalFile;
import common.entity.Request;
import common.enums.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class FindMedFileDB search for medical information in the client's clinic db.
 */
public class FindMedFileDB {


	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	public static Object handleMessage(Request request, Connection connection) {
	ArrayList<MedicalFile> list = new ArrayList<MedicalFile>();
		
	if(request.getList().size() > 1){
		String stmnt = "SELECT ghealth.medicalinfo.meType , ghealth.medicalinfo.meInfo  "
				+ " FROM ghealth.medicalinfo WHERE ghealth.medicalinfo.meClientID = ? "
				+ "AND ghealth.medicalinfo.meSpecialist = ?";

		try{
			

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;
    		
    		
    		
    		
    		preparedStatement1.setString(1,request.getList().get(0));
    		preparedStatement1.setString(2,request.getList().get(1));
    		result = preparedStatement1.executeQuery();
    		

    		if(!result.next()){

    			return Result.ERROR;
    		}
    		
    		do{
    			MedicalFile	file = new MedicalFile();
    			file.setType(result.getString(1));
    			file.setSpecialization(request.getList().get(1));
    			file.setInfo(result.getString(2));
    			list.add(file);
    		   }while(result.next());


    		
			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
		}

	}
	else{
		String stmnt = "SELECT ghealth.medicalinfo.meType,ghealth.medicalinfo.meInfo, ghealth.medicalinfo.meSpecialist  "
				+ " FROM ghealth.medicalinfo WHERE ghealth.medicalinfo.meClientID = ? ";

		try{
			

    		PreparedStatement preparedStatement1 = connection.prepareStatement(stmnt);    				
    		ResultSet result;
    		
    		
    		
    		
    		preparedStatement1.setString(1,request.getList().get(0));
    		result = preparedStatement1.executeQuery();
    		

    		if(!result.next()){

    			return Result.ERROR;
    		}
    		
    		do{
    			MedicalFile	file = new MedicalFile();
    			file.setType(result.getString(1));
    			file.setSpecialization(result.getString(3));
    			file.setInfo(result.getString(2));
    			list.add(file);
    		   }while(result.next());


			  
    	} catch (SQLException e) {
					e.printStackTrace();
					return Result.ERROR;
		}
		
	}
	return list;
	
	}

}
