package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import client.entity.Examination;
import common.entity.Request;
import common.enums.Result;


// TODO: Auto-generated Javadoc
/**
 * CreateExaminationViewDB class gets an examination from the Data base.
 * @author shays
 *
 */

public class CreateExaminationViewDB {

	/**
	 * HandleMessage function process the request from client, sends SQL queries to mySQL database by using JDBC connector
	 * The function process the queries results then return a message to the client with the requested details.
	 * @param request The request object that send by the client
	 * @param connection JDBC connection parameter
	 * @return return Object type. each result may return different type of objects.
	 */
	
	public static Object handleMessage(Request request, Connection connection) {    //take examination details to show on the screen
		
		ArrayList<byte[]> pictures = new ArrayList<byte[]>();
		Examination exam = new Examination();
		Examination exam1 = new Examination();
		exam = (Examination)request.getEntity();
		
		try {
			Statement stmnt = connection.createStatement();
		    ResultSet res = stmnt.executeQuery("SELECT * FROM ghealth.examination WHERE ghealth.examination.exID ='"+exam.getId()+"'");
		   
		
		    
		    
		    if (res.next()) {
		    	exam1.setId(res.getInt(1));
		    	exam1.setDetails(res.getString(2));
		    	
		    	for(int i =0 ;i<4;i++)
		    	if(res.getBytes(i+3) != null)
		       pictures.add(res.getBytes(i+3));    //get only the pictures that not null
		    	
		       exam1.setPictures(pictures);
		     }
		    else return Result.ERROR;
		 
		    
		    
		
	}
	    	
		 catch (SQLException e) {
			    e.printStackTrace();
			    return Result.ERROR;
			}
		
		return exam1;
	}

}
