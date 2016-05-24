package client.boundry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import client.control.CancelAppointmentController;
import client.control.ClientConnectionController;
import client.interfaces.IUi;
import common.entity.Request;
import common.enums.Command;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;


	/**
	 * CancelAppointmentUI class connects between the java code and the CancelAppointmentUI FXML file
 * The class implements IUi interface
 * @author shays
 *
 */
public class CancelAppointmentUI implements IUi{
	
	private static Stage mainStage;
	private String user_id;

	/**
	 * CancelAppointmentUI constructor
	 * @param clientID Gets the client id
	 */
	public CancelAppointmentUI(String clientID) {
		user_id = clientID;
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
	 * @see client.interfaces.IUi#displayUserWindow()
	 */
	@Override
	public void displayUserWindow() {
					
			Platform.runLater(new Runnable() {
			
				@Override
				public void run() {
					 URL url_fxml = LoginUI.class.getResource("/client/boundry/fxml/CancelAppointmentUI.fxml");
					 FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
					 Parent root;
					try {
							root = (Parent) fxmlLoader.load();
							Stage stage = new Stage();
							stage.setTitle("Cancel Appointment");
							CancelAppointmentController controller = fxmlLoader.getController();
							controller.setUser(user_id);
							URL url_32 = LoginUI.class.getResource("/img/icon_32.png");
							stage.getIcons().add(new Image(url_32.toString()));
							Scene scene = new Scene(root);
							scene.getStylesheets().add("client/boundry/css/Style.css");
							stage.setScene(scene); 
							stage.setResizable(false);
							stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					            public void handle(WindowEvent we) {
					            	
					        		ArrayList<String> username = new ArrayList<String>();
					        		username.add(ClientConnectionController.clientConnect.userName);
					        	
					        		Request request = new Request(Command.LOGOUT, username);

					        		try {
					        			ClientConnectionController.clientConnect.sendToServer(request);
					        		} catch (IOException e) {
					        			e.printStackTrace();
					        		}
					            
					           }
					     });
					      stage.show();	
					      mainStage = stage;
					    
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
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