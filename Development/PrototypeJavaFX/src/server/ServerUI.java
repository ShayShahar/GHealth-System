package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerUI extends Application{
		
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ServerUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("server/Style.css");
    primaryStage.setTitle("GHealth Server Prototype");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

}
