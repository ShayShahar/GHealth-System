package client.boundry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import client.control.ClientConnectionController;
import client.control.TransferDetailsController;
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
// TODO: Auto-generated Javadoc

/**
 * TransferDetailsUI class connects between the java code and the TransferDetailsUI FXML file
 * The class implements IUi interface.
 *
 * @author asaf
 */
@SuppressWarnings("unused")
public class TransferDetailsUI implements IUi{
	
	/** The main stage. */
	private static Stage mainStage;
	
	/** The p name. */
	private String pName;
	
	/** The name. */
	private String fName;
	
	/** The person id. */
	private String personId;
	
	/** The add. */
	private String add;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The email. */
	private String email;
	
	/** The client id. */
	private String clientId;
	
	/** The user id. */
	private int userId;
	
	/** The user name. */
	private String userName;
	
	/**
	 * TransferDetailsUI constructor.
	 *
	 * @param pName Gets the personal Name
	 * @param fName Gets the family Name
	 * @param personId Gets the personal Id
	 * @param add Gets the adders
	 * @param phoneNumber Gets the phone Number
	 * @param email Gets the email
	 * @param clientId Gets the client id
	 * @param userName Gets the user Name
	 */

	public TransferDetailsUI(String pName, String fName, String personId, String add, String phoneNumber, String email,
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
	 * @see client.interfaces.IUi#hideWindow()
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
				  URL url_fxml = TransferDetailsUI.class.getResource("/client/boundry/fxml/TransferDetailsUI.fxml");
				  FXMLLoader fxmlLoader = new FXMLLoader(url_fxml);
				  Parent root;
					try {
						root = (Parent) fxmlLoader.load();
					    Stage stage = new Stage();
					      stage.setTitle("Transfer Details");
					      TransferDetailsController controller = fxmlLoader.getController();
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