package client.boundry;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ClientConnectionUI {


		public static void displayWindow() throws IOException {

				URL url_fxml = ClientConnectionUI.class.getResource("ClientConnectionUI.fxml");
	      FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
	      stage.setTitle("GHealth Conncetion Settings");
				URL url_32 = ClientConnectionUI.class.getResource("/img/icon_32.png");
				URL url_64 = ClientConnectionUI.class.getResource("/img/icon_64.png");
				stage.getIcons().add(new Image(url_32.toString()));
				stage.getIcons().add(new Image(url_64.toString()));
	      Scene scene = new Scene(root);
	      scene.getStylesheets().add("client/boundry/Style.css");
        stage.setScene(scene);  
        stage.show();
		
		}
					

}
