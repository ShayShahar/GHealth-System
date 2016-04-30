package client;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ClientConnectionUI extends Application{

		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			displayLoginWindow(primaryStage);
		}
		
		public static void displayLoginWindow(Stage primaryStage) throws Exception{
			
			URL url_32 = ClientConnectionUI.class.getResource("/img/icon_32.png");
			URL url_64 =  ClientConnectionUI.class.getResource("/img/icon_64.png");
			Parent root = FXMLLoader.load(ClientConnectionUI.class.getResource("/client/ClientConnectionUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("client/Style.css");
			primaryStage.getIcons().add(new Image(url_32.toString()));
			primaryStage.getIcons().add(new Image(url_64.toString()));
	    primaryStage.setTitle("GHealth Client Prototype");
			primaryStage.setScene(scene);
			primaryStage.show();		
			
		}

}
