package server;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ServerUI extends Application{
		
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url_32 = getClass().getResource("/img/icon_32.png");
		URL url_64 = getClass().getResource("/img/icon_64.png");
		Parent root = FXMLLoader.load(getClass().getResource("ServerUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image(url_32.toString()));
		primaryStage.getIcons().add(new Image(url_64.toString()));
		scene.getStylesheets().add("server/Style.css");
    primaryStage.setTitle("GHealth Server Prototype");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

}
