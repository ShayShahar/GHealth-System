package server.control;

import java.sql.Connection;

import common.entity.Request;

public class insertLabReferenceDB {

	public static Object handleMessage(Request request, Connection connection) {
		
		String insertNewDate = "INSERT INTO ghealth.reference (refDate, refComments, refUrgency, client_id, specialist_id, type_id) VALUES (CURDATE(),?,?,?)";
		
		return null;
	}

}
