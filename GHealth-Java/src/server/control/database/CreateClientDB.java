package server.control.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import client.entity.Client;
import common.entity.Request;
import common.enums.Result;

public class CreateClientDB {

    public static Object handleMessage (Request request, Connection connection) {
    	
		ResultSet res = null;

		Client client = (Client) request.getEntity();
    	
    	
    	return null;
    }
    	
	
	
}
