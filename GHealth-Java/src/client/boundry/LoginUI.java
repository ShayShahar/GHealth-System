package client.boundry;

import java.net.URL;

import common.entity.Reply;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginUI implements GeneralUI{
	
	
	public static void displayLoginWindow(){
		
		try{
			URL url_fxml = LoginUI.class.getResource("ClientUI.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
      Parent root = (Parent) fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("GHealth Client Prototype");
			URL url_32 = LoginUI.class.getResource("/img/icon_32.png");
			URL url_64 = LoginUI.class.getResource("/img/icon_64.png");
			stage.getIcons().add(new Image(url_32.toString()));
			stage.getIcons().add(new Image(url_64.toString()));
      Scene scene = new Scene(root);
      scene.getStylesheets().add("client/Style.css");
      stage.setScene(scene);  
      stage.show();	
      }
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public static void displayUserWindow() {
		displayLoginWindow();
	}


	@Override
	public void handleReplyMessage(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	
}