package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClientConnectionUI extends Application{

		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("ClientConnectionUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("client/Style.css");
	    primaryStage.setTitle("GHealth Client Prototype");
			primaryStage.setScene(scene);
			primaryStage.show();		
		}

}
