package server;

import ocsf.server.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GHealthServer extends AbstractServer {

	final public static int DEFAULT_PORT = 5551;	
	private static String DB_UserName;
	private static String DB_Password;
	
	public GHealthServer(int port) {
		super(port);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/protodb?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
			System.out.println("SQL connection succeed");
			
			if (msg instanceof ArrayList) {
				if (((ArrayList<String>) msg).get(0).compareToIgnoreCase("show") == 0) {
					this.readFromDataBase(myConn,client);
				} else if (((ArrayList<String>) msg).get(0).compareToIgnoreCase("insert") == 0) {
					this.InsertToDataBase(myConn, client, ((ArrayList<String>) msg));
				}
			}
			myConn.close();
		}
		catch(SQLException e){
			System.out.println("SQL Server error");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void readFromDataBase(Connection connection, ConnectionToClient clientConection) throws SQLException{
		String table = "File Table:\n";
		
		try{
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM protodb.file");
		
			while (results.next())
				table+=results.getString(1) + " " + results.getString(2)+"\n";
				
			results.close();
			clientConection.sendToClient(table);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void InsertToDataBase(Connection connection, ConnectionToClient clientConection, ArrayList<String> list) throws SQLException{
		
		try{
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO file VALUES ('"+list.get(1)+"', '"+list.get(2)+"')";
			statement.executeUpdate(sql);
			
			System.out.println("Insert Complete");
		}
		catch (SQLException e) {
			System.out.println("Value already exists.");
		  
		}
	}
		
	@Override
	protected void serverStarted() {
		System.out.println("Server listening for connections on port "
				+ getPort());
	}

	@Override
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}
	
	public static void main (String[] args){
		
		int port;
		port = DEFAULT_PORT;
		
		GHealthServer server = new GHealthServer(port);
		DB_UserName= JOptionPane.showInputDialog("Enter DB user name");
		DB_Password= JOptionPane.showInputDialog("Enter DB password");
		
		try {
			server.listen(); // Start listening for connections.
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}

}
