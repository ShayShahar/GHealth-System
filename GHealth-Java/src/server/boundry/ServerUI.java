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

// TODO: Auto-generated Javadoc
/**
 * ServerUI extends JavaFX Application.
 * This class handles the main entry for the server application and connects between the java-code to the ServerUI FXML file
 * @author shays
 *
 */
public class ServerUI extends Application{
	
	/**
	 * main program entry.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
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
	
	/**
	 * Display an information message.
	 *
	 * @param title - Gets the message's title
	 * @param information - Gets the message's text
	 */
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
	
	/**
	 * Display an error message.
	 *
	 * @param title - Gets the message's title
	 * @param information - Gets the message's text
	 */
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
