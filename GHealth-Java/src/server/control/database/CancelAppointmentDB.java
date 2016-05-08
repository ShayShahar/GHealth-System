package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.entity.Request;
import common.enums.Result;

public class CancelAppointmentDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
		ResultSet res = null;

		/*
		Client client = (Client) request.getEntity();
	
		String searchPerson = "SELECT * FROM ghealth.person WHERE personID=?";
		String createPerson = "INSERT INTO ghealth.person (personID, personName, personFamily, personEmail, personPhone, personAddress) VALUES (?,?,?,?,?,?)";
		String createClient = "INSERT INTO ghealth.clients (person, clientClinic, joinDate) VALUES (?,?,NOW())";

		
		try {
		    PreparedStatement preparedStatement1 = connection.prepareStatement(searchPerson);
		    preparedStatement1.setString(1,client.getId());
		    res = preparedStatement1.executeQuery();
		    if (res.next()) {
		    	res.close();
		    	return Result.PERSON_EXISTS;
		    }
		    else{
		    	
		    	PreparedStatement preparedStatement2 = connection.prepareStatement(createPerson);
		    	preparedStatement2.setString(1,client.getId());
		    	preparedStatement2.setString(2,client.getName());
		    	preparedStatement2.setString(3,client.getFamilyName());
		    	preparedStatement2.setString(4,client.getEmail());
		    	preparedStatement2.setString(5,client.getPhone());
		    	preparedStatement2.setString(6,client.getAddress());
			    preparedStatement2.executeUpdate();
			    
		    	PreparedStatement preparedStatement3 = connection.prepareStatement(createClient);
		    	preparedStatement3.setString(1,client.getId());
		    	preparedStatement3.setString(2,client.getClinic());		    	
			    preparedStatement3.executeUpdate();
			    
			    return Result.OK;
			    
		    }
		    
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return Result.ERROR;
		}
		*/
	}
	
	
}
