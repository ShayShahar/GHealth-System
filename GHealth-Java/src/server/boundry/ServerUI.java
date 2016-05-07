package server.boundry;

import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import server.control.ServerController;

public class ServerUI extends Application{
		
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url_32 = getClass().getResource("/img/icon_32.png");
		Parent root = FXMLLoader.load(getClass().getResource("ServerUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image(url_32.toString()));
		scene.getStylesheets().add("server/boundry/Style.css");
    primaryStage.setTitle("GHealth Server Connection");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public static void displayMessage (String title, String information){
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
	
	public static void displayErrorMessage (String title, String information){
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
