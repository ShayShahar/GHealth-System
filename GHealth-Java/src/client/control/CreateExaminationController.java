package client.control;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;

import java.util.logging.Logger;

import javax.imageio.ImageIO;

import client.boundry.CreateAppointmentUI;
import client.boundry.CreateExaminationUI;
import client.boundry.DispatcherUI;
import client.boundry.LabWorkerUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class CreateExaminationController implements IController, Initializable{
	
	@FXML private TextField CErefnum;
	@FXML private ComboBox ExamTypes;
	@FXML private ImageView ImagePick,ImagePick1,ImagePick2,ImagePick3;
	
	
	IUi thisUi = null;
	private boolean ispicture = false;
	
	final FileChooser fileChooser = new FileChooser();
	 private Desktop desktop = Desktop.getDesktop();
	
	ObservableList<String> list = FXCollections.observableArrayList();
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		
		ImagePick.setDisable(true);
		ImagePick1.setDisable(true);
		ImagePick2.setDisable(true);
		ImagePick3.setDisable(true);
		list.add(ExaminationController.Curr_Ref.getType());
		ExamTypes.setItems(list);
		ExamTypes.getSelectionModel().select(0);
		CErefnum.setText(Integer.toString(ExaminationController.Curr_Ref.getRefNum()));
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateExaminationUI){
				thisUi = ui;
			}
		}
		
		
		
		
	}
	
	
	public void OnSendClickButton()
	{
		
	}
	
	public void OnAddPictureClickButton()
	{
		InputStream is = null;
		Image pic = null;
		configureFileChooser(fileChooser);
		 fileChooser.setTitle("Save Image");
		
		 File file = fileChooser.showOpenDialog(CreateExaminationUI.mainStage);
		
        	 if (file != null) {
                 // ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),
				    // null), "png", file);

        		 try {
					is = new FileInputStream(file.getPath());  // get the file path
	        		 pic = new Image(is,100,100,false,false);  // resize the picture
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
				 //choose where to put the picture
				if(ImagePick.isDisabled())
				{
				 ImagePick.setDisable(false);
				 ImagePick.setImage(pic);
				}
				else if(ImagePick1.isDisabled())
				    {
					 ImagePick1.setDisable(false);
					 ImagePick1.setImage(pic);
					}
				else if(ImagePick2.isDisabled())
			    {
				 ImagePick2.setDisable(false);
				 ImagePick2.setImage(pic);
				}
				else if(ImagePick3.isDisabled())
			    {
				 ImagePick3.setDisable(false);
				 ImagePick3.setImage(pic);
				 
				 ImagePick.setDisable(true);
					ImagePick1.setDisable(true);
					ImagePick2.setDisable(true);
					ImagePick3.setDisable(true);
				}
				
             }
             
        
            
         
         
	}
	
	

	
	
	
	
	

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public void onLogoutButtonClick(ActionEvent event){
			
			ArrayList<String> username = new ArrayList<String>();
			username.add(UserController.getUser());
		
			Request request = new Request(Command.LOGOUT, username);

			try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	 
	 public void onBackButtonClick()
	 {
		 thisUi.hideWindow();
			
			for (IUi ui : ClientConnectionController.clientConnect.userInterface){
				if (ui instanceof LabWorkerUI){
						ui.showWindow();
				}
			}
			
			ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	 }
	 
	 
	 private void openFile(File file) {
	        try {
	            desktop.open(file);
	        } catch (IOException ex) {
	            Logger.getLogger(
	            		CreateExaminationUI.class.getName()).log(
	                    Level.SEVERE, null, ex
	                );
	        }
	        
	      
}
	 
	  private static void configureFileChooser( final FileChooser fileChooser)
      {   
		  
          fileChooser.setTitle("View Pictures");
          fileChooser.setInitialDirectory(
              new File(System.getProperty("user.home"))
          );                 
          fileChooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("All Images", "*.*"),
              new FileChooser.ExtensionFilter("JPG", "*.jpg"),
              new FileChooser.ExtensionFilter("PNG", "*.png")
          );
  }
	  

}
