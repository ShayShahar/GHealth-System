package client.boundry;

import java.net.URL;

import client.control.ClientConnectionController;
import client.interfaces.IUi;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class GeneralManagerUI implements IUi{

	@Override
	public void hideWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayUserWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayErrorMessage(String title, String information) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = LoginUI.class.getResource("/img/error.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("Error Message");
				dialog.setHeaderText(title);
				dialog.getDialogPane().getStylesheets().add("client/boundry/css/JMetroLightTheme.css");
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
			}
		});			
	}
	
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
				dialog.getDialogPane().getStylesheets().add("client/boundry/css/JMetroLightTheme.css");
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
				}
		});
	}

	@Override
	public void closeWindow() {
		// TODO Auto-generated method stub
		
	}


	
}