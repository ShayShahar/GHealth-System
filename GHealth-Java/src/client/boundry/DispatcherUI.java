package client.boundry;

import java.io.IOException;
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

public class DispatcherUI implements IUi{
	
	private static Stage mainStage;
	
	@Override
	public void hideWindow() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
					mainStage.hide();
			}
			});		
	}


	@Override
	public void showWindow() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
					mainStage.show();	
			}
			});		
	}

	@Override
	public void displayUserWindow() {
					
			Platform.runLater(new Runnable() {
			
				@Override
				public void run() {
					URL url_fxml = LoginUI.class.getResource("/client/boundry/fxml/DispatcherUI.fxml");
				  FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
				  Parent root;
					try {
								root = (Parent) fxmlLoader.load();
					      Stage stage = new Stage();
					      stage.setTitle("Dispatcher Menu");
								URL url_32 = LoginUI.class.getResource("/img/icon_32.png");
								URL url_64 = LoginUI.class.getResource("/img/icon_64.png");
								stage.getIcons().add(new Image(url_32.toString()));
								stage.getIcons().add(new Image(url_64.toString()));
					      Scene scene = new Scene(root);
					      //scene.getStylesheets().add("client/boundry/css/JMetroLightTheme.css");
					      stage.setScene(scene); 
					      stage.setResizable(false);
					      stage.show();	
					      mainStage = stage;
					    
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			});		
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