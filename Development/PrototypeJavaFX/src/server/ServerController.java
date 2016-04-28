package server;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ServerController extends AbstractServer{
	
	@FXML private TextField usernameTextbox;
	@FXML private PasswordField passField;

	final public static int DEFAULT_PORT = 5551;	
	private static String DB_UserName;
	private static String DB_Password;
	
	public ServerController(){
		super(DEFAULT_PORT);
	}
	
	public ServerController(int port) {
		super(port);
	}

	public void loginHandler(ActionEvent event){
		DB_UserName = usernameTextbox.getText();
		DB_Password = passField.getText();
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
			myConn.close();		
			
			try {
				this.listen();
			} catch (Exception ex) {
				
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Server Error");
				alert2.setHeaderText("Unknown server error");
				alert2.setContentText("Try re-connect to server");
				alert2.showAndWait();
				
				return;
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connection Succeed");
			alert.setHeaderText("MySQL Connected Successfully");
			alert.setContentText("View server information on console.");
			alert.showAndWait();
			
	    Stage stage = (Stage) usernameTextbox.getScene().getWindow();
	    stage.close();
			
			


	   

		}	
		catch(SQLException e){
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Invalid Username / Password");
			alert.setContentText("Try again...");
			alert.showAndWait();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
  }

	@Override
	@SuppressWarnings("unchecked")
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
			System.out.println("SQL connection succeed");
			
			if (msg instanceof ArrayList) {
				if (((ArrayList<String>) msg).get(0).compareToIgnoreCase("show") == 0) {
					this.readFromDataBase(myConn,client);
				} else if (((ArrayList<String>) msg).get(0).compareToIgnoreCase("update") == 0) {
					this.updateValueInDB(myConn, client, ((ArrayList<String>) msg));
				}
			}
			myConn.close();
		}
		catch(SQLException e){
		 e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void readFromDataBase(Connection connection, ConnectionToClient clientConection) throws SQLException{
		ArrayList<String> table = new ArrayList<String>();
		
		table.add("show");
				
		try{
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM test.physician;");
		
			while (results.next()){
				table.add(results.getString(1));
				table.add(results.getString(2));
			}
			
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
	
	private void updateValueInDB(Connection connection, ConnectionToClient clientConection, ArrayList<String> list) throws SQLException{
		ArrayList<String> msg = new ArrayList<String>();
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE test.physician SET pscSpecialization='"
					+ list.get(2) + "'where pscName='" + list.get(1) +"';");
		}
		catch (SQLException e) {
			try{
				msg.add("update");
				msg.add("error");
				clientConection.sendToClient(msg);
				System.out.println("Update Error");
			}
			catch(Exception ex){
				ex.printStackTrace();
			}		  
		}
		
		catch(Exception e){
			try{
				msg.add("update");
				msg.add("error");
				clientConection.sendToClient(msg);
				System.out.println("Update Error");
			}
			catch(Exception ex){
				ex.printStackTrace();
			}		 
		}
		
		try{
			msg.add("update");
			msg.add("succeed");
			clientConection.sendToClient(msg);
		}
		catch(Exception e){
			e.printStackTrace();
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

}

