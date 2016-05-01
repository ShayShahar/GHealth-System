package client.boundry;

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

public class LoginUI extends Application{
	
	public static void main (String[] args){
		displayLoginWindow();
	}
	
	public static void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = LoginUI.class.getResource("/img/info.png");
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
				URL url = LoginUI.class.getResource("/img/error.png");
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

	
	public static void displayLoginWindow(){
			launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url_32 = getClass().getResource("/img/icon_32.png");
		URL url_64 = getClass().getResource("/img/icon_64.png");
		Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("client/boundry/Style.css");
		primaryStage.getIcons().add(new Image(url_32.toString()));
		primaryStage.getIcons().add(new Image(url_64.toString()));
    primaryStage.setTitle("GHealth Client Connection Setup");
		primaryStage.setScene(scene);
		primaryStage.show();				
	}
	
}