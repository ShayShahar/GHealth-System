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

import client.boundry.CreateExaminationUI;
import client.boundry.LabWorkerUI;
import client.entity.Examination;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class CreateExaminationController implements IController, Initializable{
	
	@FXML private TextField CErefnum;
	@FXML private ComboBox ExamTypes;
	@FXML private ImageView ImagePick,ImagePick1,ImagePick2,ImagePick3;
	@FXML private Button Xbtn1,Xbtn2,Xbtn3,Xbtn4;
	@FXML private TextArea ExamTextArea;
	
	
	IUi thisUi = null;
	private boolean ispicture = false;
	Examination exam;
	
	final FileChooser fileChooser = new FileChooser();
	 private Desktop desktop = Desktop.getDesktop();
	
	ObservableList<String> list = FXCollections.observableArrayList();
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		Xbtn1.setDisable(true);
		Xbtn2.setDisable(true);
		Xbtn3.setDisable(true);
		Xbtn4.setDisable(true);
		//ImagePick.setDisable(true);
		//ImagePick1.setDisable(true);
		//ImagePick2.setDisable(true);
		//ImagePick3.setDisable(true);
		list.add(ExaminationController.Curr_Ref.getType());
		ExamTypes.setItems(list);
		ExamTypes.getSelectionModel().select(0);
		CErefnum.setText(Integer.toString(ExaminationController.Curr_Ref.getRefNum()));
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateExaminationUI){
				thisUi = ui;
			}
		}
		
		
		if(ExaminationController.Curr_Ref.getCode() != 0)   // view Examination Details
		{
			 Request request = null;
			 exam = new Examination();
			 exam.setId(ExaminationController.Curr_Ref.getCode());
			 request = new Request(Command.CREATE_EXAMINATION_VIEW, exam);
			
			 
			 try {
					ClientConnectionController.clientConnect.controller = this;
					ClientConnectionController.clientConnect.sendToServer(request);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	
		
		
		
		
	}
		
	public void OnAddPictureClickButton(int i)
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
				 
                switch(i)
                {
                	case 1:
                	{
                		 ImagePick.setImage(pic);
                		 Xbtn1.setDisable(false);
                		 Xbtn1.setVisible(true);
                		return;
                	}
                	case 2:
                	{
                		 ImagePick1.setImage(pic);
                		 Xbtn2.setDisable(false);
                		 Xbtn2.setVisible(true);
                		return;
                	}
                	case 3:
                	{
                		 ImagePick2.setImage(pic);
                		 Xbtn3.setDisable(false);
                		 Xbtn3.setVisible(true);
                		return;
                	}
                	case 4:
                	{
                		 ImagePick3.setImage(pic);
                		 Xbtn4.setDisable(false);
                		 Xbtn4.setVisible(true);
                		return;
                	}
                	
                	default:
                		return;
                	
                }
				 //choose where to put the picture
        		 
        		 /*
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
				*/
             }
             
        
            
         
         
	}
	
	public void OnAddPictureClickButton1()
	{
		 OnAddPictureClickButton(1);
		
		 
	}
	
	public void OnAddPictureClickButton2()
	{
		 OnAddPictureClickButton(2);
	}

	public void OnAddPictureClickButton3()
	{
		 OnAddPictureClickButton(3);
	}
	
	public void OnAddPictureClickButton4()
	{
		 OnAddPictureClickButton(4);
	}
	
	
	public void OnClosePictureClickButton()
	{
		InputStream is = null;
		Image pic = null;
		try {
			is = new FileInputStream("src/img/imagehere.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // get the file path
		 pic = new Image(is,100,100,false,false);  // resize the picture
		 ImagePick.setImage(pic);
		 Xbtn1.setDisable(true);
		 Xbtn1.setVisible(false);
	}
	
	public void OnClosePictureClickButton1()
	{
		InputStream is = null;
		Image pic = null;
		try {
			is = new FileInputStream("src/img/imagehere.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // get the file path
		 pic = new Image(is,100,100,false,false);  // resize the picture
		 ImagePick1.setImage(pic);
		 Xbtn2.setDisable(true);
		 Xbtn2.setVisible(false);
	}
	
	public void OnClosePictureClickButton2()
	{
		InputStream is = null;
		Image pic = null;
		try {
			is = new FileInputStream("src/img/imagehere.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // get the file path
		 pic = new Image(is,100,100,false,false);  // resize the picture
		 ImagePick2.setImage(pic);
		 Xbtn3.setDisable(true);
		 Xbtn3.setVisible(false);
	}
	
	public void OnClosePictureClickButton3()
	{
		InputStream is = null;
		Image pic = null;
		try {
			is = new FileInputStream("src/img/imagehere.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // get the file path
		 pic = new Image(is,100,100,false,false);  // resize the picture
		 ImagePick3.setImage(pic);
		 Xbtn4.setDisable(true);
		 Xbtn4.setVisible(false);
	}
	
	
	
	

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
		
		Object result =  reply.getResult();
		
		
		if(result instanceof Result)
		{
			
		if ((Result)result == Result.ERROR){
			System.out.println("3");
			ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
				System.exit(1);
		}
		
		
		}
		
		if (reply.getCommand() == Command.CREATE_EXAMINATION )
		{
			
			
			
			ExaminationController.Curr_Ref.setCode(((int)result));
			System.out.println((int)result);
			onBackButtonClick();
			ClientConnectionController.clientConnect.userInterface.get(1).displayMessage ("CREATE", "Examination Successfully CREATED");
		}
		
		if (reply.getCommand() == Command.CREATE_EXAMINATION_VIEW )
		{
			System.out.print(".CREATE_EXAMINATION_VIEW");
			Examination exam = (Examination)result;
			ExamTextArea.setText(exam.getDetails());
			
			
			
		}
		
		if (reply.getCommand() == Command.CREATE_EXAMINATION_UPDATE )
		{
			onBackButtonClick();
			ClientConnectionController.clientConnect.userInterface.get(1).displayMessage ("UPDATE", "Examination Successfully Updated");
			
		}
		
			 
			 
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
	 
	 
	 public void OnSendClickButton()
	 {
		 Request request = null;
		 
		 if(ExaminationController.Curr_Ref.getCode() == 0)  //check if there is exist examination	 
		 {
			
		 Examination exam = new Examination();
		 exam.setRef_id(ExaminationController.Curr_Ref.getRefNum());
         exam.setDetails(ExamTextArea.getText());
		 request = new Request(Command.CREATE_EXAMINATION, exam);
		
		 
		 
		 }
		 else
		 {
			 this.exam.setDetails(ExamTextArea.getText());
			 request = new Request(Command.CREATE_EXAMINATION_UPDATE, exam);
		 }
		 
		 try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(request);
			} catch (IOException e) {
				e.printStackTrace();
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
