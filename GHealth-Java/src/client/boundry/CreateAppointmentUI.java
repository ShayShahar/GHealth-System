package client.boundry;

import java.io.IOException;
import java.net.URL;

import client.control.ClientConnectionController;
import client.control.CreateAppointmentController;
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

public class CreateAppointmentUI implements IUi{
	
	private static Stage mainStage;
	private String user_id;
	private int id;

	
	public CreateAppointmentUI(String clientID, int id) {
		user_id = clientID;
		this.id = id;
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
	public void displayUserWindow() {
					
			Platform.runLater(new Runnable() {
			
				@Override
				public void run() {
					URL url_fxml = LoginUI.class.getResource("/client/boundry/fxml/CreateAppointmentUI.fxml");
				  FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
				  Parent root;
					try {
								root = (Parent) fxmlLoader.load();
					      Stage stage = new Stage();
					      stage.setTitle("Create Appointment");
					      CreateAppointmentController controller = fxmlLoader.getController();
					      controller.setUser(user_id);
					      controller.setID(id);
								URL url_32 = LoginUI.class.getResource("/img/icon_32.png");
								stage.getIcons().add(new Image(url_32.toString()));
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



	
}