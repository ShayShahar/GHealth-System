package client.control;

import java.net.URL;

import client.boundry.LoginUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


public class ClientConnectionController{

	@FXML private TextField ipAddress;
	@FXML private TextField port;

	public static int PORT;
	public static String IP_ADDR;

	public static ClientController clientConnect;
	
	public void onConnectButtonClick(ActionEvent event)
	{
		try{
			
			ipAddress.setStyle("-fx-prompt-text-fill: gray");

			if (ipAddress.getText() == null || ipAddress.getText().trim().isEmpty() ){
				displayErrorMessage("Connection Error", "Some required fields are missing.");
				ipAddress.setStyle("-fx-prompt-text-fill: #ffa0a0");
				return;
			}
		
				clientConnect = new ClientController(ipAddress.getText(),PORT);
				IP_ADDR = ipAddress.getText();
				displayMessage("Connection Succeed","Connected to server at " + ipAddress.getText() + " on PORT " + PORT);

		    try {
		    		LoginUI.displayLoginWindow();
		        ((Node)(event.getSource())).getScene().getWindow().hide();
		      
		    }catch (Exception ex) {
		            ex.printStackTrace();
		    }
			
		}
		catch (Exception e){
			displayErrorMessage("Connection Failed","Error occured while trying to connect to " + ipAddress.getText() + " on PORT " + PORT);
		}
	  
	}
	
	private void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = ClientConnectionController.class.getResource("/img/info.png");
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
				URL url = ClientConnectionController.class.getResource("/img/error.png");
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
