package client;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientUI {

	public static void displayClientWindow(){
		

	try {
		
		URL url_fxml = ClientUI.class.getResource("ClientUI.fxml");
	  FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
	  Parent root;
		root = (Parent) fxmlLoader.load();
	  Stage stage = new Stage();
	  stage.setTitle("GHealth Client Prototype");
	  URL url_32 = ClientUI.class.getResource("/img/icon_32.png");
		URL url_64 = ClientUI.class.getResource("/img/icon_64.png");
		stage.getIcons().add(new Image(url_32.toString()));
		stage.getIcons().add(new Image(url_64.toString()));
		Scene scene = new Scene(root);
	  scene.getStylesheets().add("client/Style.css");
    stage.setScene(scene);  
    stage.show();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	
}
