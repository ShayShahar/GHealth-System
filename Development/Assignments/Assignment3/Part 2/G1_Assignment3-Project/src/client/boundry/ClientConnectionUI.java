package client.boundry;

import java.net.URL;
import client.control.ClientConnectionController;
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

// TODO: Auto-generated Javadoc
/**
 * ClientConnectionUI class is the main entry for the GHealth System Application
 * The class inherits from Application class to implement JavaFX application.
 * @author shays
 *
 */
public class ClientConnectionUI extends Application{

		/**
		 * The main method.
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
		
			URL url_32 = ClientConnectionUI.class.getResource("/img/icon_32.png");
			Parent root = FXMLLoader.load(ClientConnectionUI.class.getResource("/client/boundry/fxml/ClientConnectionUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image(url_32.toString()));
	    primaryStage.setTitle("GHealth Client Connection");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();		
			
		}

		/**
		 * display an error message on the screen.
		 *
		 * @param title Gets the title of the message
		 * @param information Gets extra information to display in the message
		 */
		public static void displayErrorMessage(String title, String information) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					URL url = LoginUI.class.getResource("/img/error.png");
					Dialog<Pair<String, String>> dialog = new Dialog<>();
					dialog.setTitle("Error Message");
					dialog.setHeaderText(title);
					dialog.setContentText(information);
					dialog.setGraphic(new ImageView(url.toString()));
					dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
					dialog.showAndWait();
				}
			});			
		}
		
		/**
		 * display information message on the screen.
		 * @param title Gets the title of the message
		 * @param information Gets extra information to display in the message
		 */
		public static void displayMessage(String title, String information){
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					URL url = ClientConnectionController.class.getResource("/img/info.png");
					Dialog<Pair<String, String>> dialog = new Dialog<>();
					dialog.setTitle("Information");
					dialog.setHeaderText(title);
					dialog.setContentText(information);
					dialog.setGraphic(new ImageView(url.toString()));
					dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
					dialog.showAndWait();
					}
			});
		}



}
