package server.control;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import server.boundry.ServerUI;
import common.entity.*;

public class ServerController extends AbstractServer{
	
	@FXML private TextField usernameTextbox;
	@FXML private PasswordField passField;
	@FXML private Button loginBtn;
	@FXML private TabPane tabPane;
	@FXML private Tab connectionTab;
	@FXML private Tab notificationTab;
	@FXML private TextArea notificationsFTxt;

	final public static int DEFAULT_PORT = 5551;	
	private static String DB_UserName;
	private static String DB_Password;
	
	public ServerController(){
		super(DEFAULT_PORT);
	}
	
	public ServerController(int port) {
		super(port);
	}

	public void onClickConnectButton(ActionEvent event){
		
	if (loginBtn.getText().equals("Connect")){
		
		DB_UserName = usernameTextbox.getText();
		DB_Password = passField.getText();
		
		usernameTextbox.setStyle("-fx-prompt-text-fill: gray");
		passField.setStyle("-fx-prompt-text-fill: gray");
		
		if (usernameTextbox.getText() == null || usernameTextbox.getText().trim().isEmpty() || passField.getText() == null || passField.getText().trim().isEmpty()){
			ServerUI.displayErrorMessage("Update Error", "Some required fields are missing.");
			if ((usernameTextbox.getText() == "" || usernameTextbox.getText().trim().isEmpty()) && (passField.getText() == "" || passField.getText().trim().isEmpty()) ){
				usernameTextbox.setStyle("-fx-prompt-text-fill: #ffa0a0");
				passField.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			else if ((usernameTextbox.getText() == "" || usernameTextbox.getText().trim().isEmpty()) && !(passField.getText() == "" || passField.getText().trim().isEmpty())){
				usernameTextbox.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			else if (!(usernameTextbox.getText() == "" || usernameTextbox.getText().trim().isEmpty()) && (passField.getText() == "" || passField.getText().trim().isEmpty())){
				passField.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ghealth?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
			myConn.close();		
			
			try {
				this.listen();
			} catch (Exception ex) {
				ServerUI.displayErrorMessage("Unknown server error","Try re-connect to server");
				return;
			}
			
			ServerUI.displayMessage("MySQL Connected Successfully","View server information on console.");

			loginBtn.setText("Disconnect");
			tabPane.getSelectionModel().selectNext();
			usernameTextbox.setDisable(true);
			passField.setDisable(true);

		}	
		catch(SQLException e){
			ServerUI.displayErrorMessage("Connection Error","Invalid Username / Password");
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	else {
		
		try {
			this.close();
			loginBtn.setText("Connect");
			usernameTextbox.setDisable(false);
			passField.setDisable(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
 }

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		Request request = (Request) msg;
		Reply reply = null;
		
		try{
			
				Class.forName("com.mysql.jdbc.Driver").newInstance();		
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ghealth?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
				notificationsFTxt.appendText("SQL connection succeed\n");
			
		    reply = new Reply(DBController.processRequest(request, myConn), request.getCommand());
		    //System.out.println((String)reply.getResult());
			try {
			    client.sendToClient(reply);
			} catch (IOException e) {
			    e.printStackTrace();
			}				
			myConn.close();

		}catch(SQLException e){
			 e.printStackTrace();
		}
		 catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void serverStarted() {
		
		notificationsFTxt.appendText("Server listening for connections on port "
				+ getPort()+"\n");
	}

	@Override
	protected void serverStopped() {
		
		notificationsFTxt.appendText("Server has stopped listening for connections.\n");
	}

}

