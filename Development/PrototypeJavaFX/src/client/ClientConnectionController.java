package client;

import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


public class ClientConnectionController{

	@FXML private TextField ipAddress;
	final public static int DEFAULT_PORT = 5551;
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
			
			
				clientConnect = new ClientController(ipAddress.getText(),DEFAULT_PORT);
				IP_ADDR = ipAddress.getText();
				displayMessage("Connection Succeed","Connected to server at " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);

		    try {
		    		URL url_fxml = getClass().getResource("ClientUI.fxml");
		        FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
		        stage.setTitle("GHealth Client Prototype");
						URL url_32 = getClass().getResource("/img/icon_32.png");
						URL url_64 = getClass().getResource("/img/icon_64.png");
						stage.getIcons().add(new Image(url_32.toString()));
						stage.getIcons().add(new Image(url_64.toString()));
		        Scene scene = new Scene(root);
		        scene.getStylesheets().add("client/Style.css");
            stage.setScene(scene);  
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
