package client.boundry;

import java.net.URL;

import client.interfaces.IUi;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginUI implements IUi{
	
	private static Stage mainStage;

	
	public void displayLoginWindow(){
		
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
					URL url_64 = LoginUI.class.getResource("/img/icon_64.png");
					stage.getIcons().add(new Image(url_32.toString()));
					stage.getIcons().add(new Image(url_64.toString()));
		      Scene scene = new Scene(root);
		      scene.getStylesheets().add("client/boundry/css/Style.css");
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

	public static void hideWindow(){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
					mainStage.hide();
			}
			});
	}
	
	public static void showWindow(){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
					mainStage.show();	
			}
			});
	}
	
}