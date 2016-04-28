package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class ClientConnectionController{

	@FXML private TextField ipAddress;
	final public static int DEFAULT_PORT = 5551;
	public static ClientController clientConnect;
	
	public void onConnectButtonClick(ActionEvent event)
	{
		try{
				clientConnect = new ClientController(ipAddress.getText(),DEFAULT_PORT);
				
				displayMessage("Connection Succeed","Connected to server at " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);

		    try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
		        stage.setTitle("GHealth Client Prototype");
            stage.setScene(new Scene(root));  
            stage.show();
		        ((Node)(event.getSource())).getScene().getWindow().hide();
		      
		    }catch (Exception ex) {
		            ex.printStackTrace();
		    }
			
		}
		catch (Exception e){
			displayErrorMessage("Connection Failed","Error occured while trying to connect to " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);
		}
	  
	}
	
	private void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setHeaderText(title);
				alert.setContentText(information);
				alert.showAndWait();					}
		});
	}
	
	private void displayErrorMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText(title);
				alert.setContentText(information);
				alert.showAndWait();					}
		});
	}

}
