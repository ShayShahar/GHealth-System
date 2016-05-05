package client.boundry;

import java.io.IOException;
import java.net.URL;

import client.interfaces.IUi;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LabWorkerUI implements IUi{
	
	private static Stage mainStage;

	@Override
	public void displayUserWindow() {
	
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url_fxml = LoginUI.class.getResource("/client/boundry/fxml/LabWorkerUI.fxml");
			      FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
			      Parent root;
				try {
					root = (Parent) fxmlLoader.load();
				      Stage stage = new Stage();
				      stage.setTitle("LabWorkerUI Menu");
							URL url_32 = LoginUI.class.getResource("/img/icon_32.png");
							URL url_64 = LoginUI.class.getResource("/img/icon_64.png");
							stage.getIcons().add(new Image(url_32.toString()));
							stage.getIcons().add(new Image(url_64.toString()));
				      Scene scene = new Scene(root);
				      scene.getStylesheets().add("client/boundry/css/Style.css");
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
	public void displayErrorMessage() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void displayMessage() {
		// TODO Auto-generated method stub
		
	}
}