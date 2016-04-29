package server;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import javafx.scene.image.ImageView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

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

	public void loginHandler(ActionEvent event){
		
	if (loginBtn.getText().equals("Connect")){
		
		DB_UserName = usernameTextbox.getText();
		DB_Password = passField.getText();
		
		usernameTextbox.setStyle("-fx-prompt-text-fill: gray");
		passField.setStyle("-fx-prompt-text-fill: gray");
		
		if (usernameTextbox.getText() == null || usernameTextbox.getText().trim().isEmpty() || passField.getText() == null || passField.getText().trim().isEmpty()){
			displayErrorMessage("Update Error", "Some required fields are missing.");
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
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
			myConn.close();		
			
			try {
				this.listen();
			} catch (Exception ex) {
				displayErrorMessage("Unknown server error","Try re-connect to server");
				return;
			}
			
			displayMessage("MySQL Connected Successfully","View server information on console.");

			loginBtn.setText("Disconnect");
			tabPane.getSelectionModel().selectNext();
			usernameTextbox.setDisable(true);
			passField.setDisable(true);

		}	
		catch(SQLException e){
			displayErrorMessage("Connection Error","Invalid Username / Password");
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
	@SuppressWarnings("unchecked")
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=true", DB_UserName ,DB_Password);
			notificationsFTxt.appendText("SQL connection succeed\n");
			
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
				notificationsFTxt.appendText("Update Error\n");
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
				notificationsFTxt.appendText("Update Error\n");
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
		
		notificationsFTxt.appendText("Server listening for connections on port "
				+ getPort()+"\n");
	}

	@Override
	protected void serverStopped() {
		
		notificationsFTxt.appendText("Server has stopped listening for connections.\n");
	}

	private void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = ServerController.class.getResource("/img/info.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("INFORMATION");
				dialog.setHeaderText(title);
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
				}
		});
	}
	
	private void displayErrorMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = ServerController.class.getResource("/img/error.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("ERROR");
				dialog.setHeaderText(title);
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
			}
		});
	}

}

