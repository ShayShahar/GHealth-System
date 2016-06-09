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

// TODO: Auto-generated Javadoc
/**
 * ServerController is the server controller of GHealth System application.
 * The class extends OCSF Abstract server.
 * @author shays
 *
 */
public class ServerController extends AbstractServer{
	
	/** The username textbox. */
	@FXML private TextField usernameTextbox;
	
	/** The pass field. */
	@FXML private PasswordField passField;
	
	/** The login btn. */
	@FXML private Button loginBtn;
	
	/** The tab pane. */
	@FXML private TabPane tabPane;
	
	/** The connection tab. */
	@FXML private Tab connectionTab;
	
	/** The notification tab. */
	@FXML private Tab notificationTab;
	
	/** The notifications f txt. */
	@FXML private TextArea notificationsFTxt;

	/** Default connection port. */
	final public static int DEFAULT_PORT = 5551;	
	
	/** The D b_ user name. */
	private static String DB_UserName;
	
	/** The D b_ password. */
	private static String DB_Password;
	
	/**
	 * ServerController default constructor.
	 */
	public ServerController(){
		super(DEFAULT_PORT);
	}

	/**
	 * ServerController constructor.
	 *
	 * @param port - Gets the server's port
	 */
	public ServerController(int port) {
		super(port);
	}

	/**
	 * onClickConnectButton handles the logics of Connect button.
	 * Validates entered fields.
	 * Opens a server socket.
	 *
	 * @param event the event
	 */
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
			
			try {
				this.listen();
			} catch (Exception ex) {
				ServerUI.displayErrorMessage("Unknown server error","Try re-connect to server");
				return;
			}
			
			Statement stmt;
			try {
				stmt = myConn.createStatement();
				stmt.executeUpdate("UPDATE ghealth.users SET userStatus=0 WHERE userName <> 'dummy'");
				myConn.close();		
					
			} catch (SQLException e1) {
				e1.printStackTrace();
				myConn.close();		
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

	/* (non-Javadoc)
	 * @see ocsf.server.AbstractServer#handleMessageFromClient(java.lang.Object, ocsf.server.ConnectionToClient)
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		Request request = (Request) msg;
		Reply reply = null;
		
		try{
			
				Class.forName("com.mysql.jdbc.Driver").newInstance();		
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ghealth?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
				notificationsFTxt.appendText("SQL connection succeed\n");
			
		    reply = new Reply(DBController.processRequest(request, myConn), request.getCommand());
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


	/* (non-Javadoc)
	 * @see ocsf.server.AbstractServer#serverStarted()
	 */
	@Override
	protected void serverStarted() {
		
		notificationsFTxt.appendText("Server listening for connections on port "
				+ getPort()+"\n");
	}

	/* (non-Javadoc)
	 * @see ocsf.server.AbstractServer#serverStopped()
	 */
	@Override
	protected void serverStopped() {
		
		notificationsFTxt.appendText("Server has stopped listening for connections.\n");
	}

}

