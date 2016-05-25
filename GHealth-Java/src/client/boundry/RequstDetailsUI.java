package client.boundry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import client.control.ClientConnectionController;
import client.control.CreateLabReferenceController;
import client.control.RequstDetailsController;
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

public class RequstDetailsUI implements IUi{
	
	private static Stage mainStage;
	public String pName; public String fName; public String personId; public String add; public String phoneNumber;
	public String email; public String clientId;
	public int userId;
	public String userName;

	public RequstDetailsUI(String pName, String fName, String personId, String add, String phoneNumber, String email,
			String clientId, String userName) {
		this.pName = pName;
		this.fName = fName;
		this.personId = personId;
		this.add = add;
		this.phoneNumber = phoneNumber;
		this.email = email;		
		this.clientId = clientId;
		this.userName = userName;
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
				  URL url_fxml = RequstDetailsUI.class.getResource("/client/boundry/fxml/RequstDetailsUI.fxml");
				  FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
				  Parent root;
					try {
						root = (Parent) fxmlLoader.load();
					    Stage stage = new Stage();
					      stage.setTitle("Requst Details");
					      RequstDetailsController controller = fxmlLoader.getController();
					      controller.setUser(pName,fName,personId,add,phoneNumber,email,clientId,userName);
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
