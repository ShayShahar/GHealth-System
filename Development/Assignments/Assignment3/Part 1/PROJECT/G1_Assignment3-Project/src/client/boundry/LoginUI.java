package client.boundry;

import java.net.URL;

import client.control.ClientConnectionController;
import client.interfaces.IUi;
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
 * LoginUI class connects between the java code and the LoginUI FXML file
 * The class implements IUi interface.
 *
 * @author shays
 */
public class LoginUI implements IUi{
	
	/** The main stage. */
	private static Stage mainStage;

	/*
	 * (non-Javadoc)
	 * @see client.interfaces.IUi#displayUserWindow()
	 */
	@Override
	public void displayUserWindow() {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				
				try{
					URL url_fxml = LoginUI.class.getResource("/client/boundry/fxml/LoginUI.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setTitle("GHealth Login");
					URL url_32 = LoginUI.class.getResource("/img/icon_32.png");
					stage.getIcons().add(new Image(url_32.toString()));
					Scene scene = new Scene(root);
					stage.setScene(scene); 
					stage.setResizable(false);
					stage.show();	
					mainStage = stage;
		    }
				catch(Exception e){
					e.printStackTrace();
				}
			}
			});
	}

	/*
	 * (non-Javadoc)
	 * @see client.interfaces.IUi#hideWindow()
	 */
	@Override
	public void hideWindow() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
					mainStage.hide();
			}
			});		
	}

	/*
	 * (non-Javadoc)
	 * @see client.interfaces.IUi#showWindow()
	 */
	@Override
	public void showWindow() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
					mainStage.show();	
			}
			});		
	}
	/*
	 * (non-Javadoc)
	 * @see client.interfaces.IUi#displayErrorMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void displayErrorMessage(String title, String information) {
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
	
	/*
	 * (non-Javadoc)
	 * @see client.interfaces.IUi#displayMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void displayMessage(String title, String information){
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