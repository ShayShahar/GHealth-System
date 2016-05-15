package client.control;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
import javax.swing.ImageIcon;

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
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

public class CreateExaminationController implements IController, Initializable{
	
	@FXML private TextField CErefnum;
	@FXML private TextField examType;
	@FXML private ImageView ImagePick,ImagePick1,ImagePick2,ImagePick3;
	@FXML private Button Xbtn1,Xbtn2,Xbtn3,Xbtn4;
	@FXML private TextArea ExamTextArea;
	private ArrayList<byte[]> pictures = new ArrayList<byte[]>();
	private String[] filenameArr = new String[4];
	private byte[][]  DBpic  = new  byte[4][];
	
	IUi thisUi = null;
	private boolean ispicture = false;
	Examination exam;
	
	final FileChooser fileChooser = new FileChooser();
	 private Desktop desktop = Desktop.getDesktop();
	
	ObservableList<String> list = FXCollections.observableArrayList();
	
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Xbtn1.setDisable(true);
		Xbtn2.setDisable(true);
		Xbtn3.setDisable(true);
		Xbtn4.setDisable(true);

		examType.setText(ExaminationController.Curr_Ref.getType());
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
		
	/**
	 * 
	 * @param i
	 */
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
                		filenameArr[0] = file.getPath();
                		 ImagePick.setImage(pic);
                		 Xbtn1.setDisable(false);
                		 Xbtn1.setVisible(true);
                		 
                		 ImagePick.setDisable(true);
                		 
                		return;
                	}
                	case 2:
                	{
                		filenameArr[1] = file.getPath();
                		 ImagePick1.setImage(pic);
                		 Xbtn2.setDisable(false);
                		 Xbtn2.setVisible(true);
                		 
                		 ImagePick1.setDisable(true);
                		 
                		return;
                	}
                	case 3:
                	{
                		filenameArr[2] = file.getPath();
                		 ImagePick2.setImage(pic);
                		 Xbtn3.setDisable(false);
                		 Xbtn3.setVisible(true);
                		 
                		 ImagePick2.setDisable(true);
                		 
                		return;
                	}
                	case 4:
                	{
                		filenameArr[3] = file.getPath();
                		 ImagePick3.setImage(pic);
                		 Xbtn4.setDisable(false);
                		 Xbtn4.setVisible(true);
                		 
                		 ImagePick3.setDisable(true);
                		 
                		return;
                	}
                	
                	default:
                		return;
                	
                }
				
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
		 filenameArr[0] = null;
		 ImagePick.setImage(pic);
		 Xbtn1.setDisable(true);
		 Xbtn1.setVisible(false);
		 
		 ImagePick.setDisable(false);
		 DBpic[0] = null;
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
		 filenameArr[1] = null;
		 ImagePick1.setImage(pic);
		 Xbtn2.setDisable(true);
		 Xbtn2.setVisible(false);
		 
		 ImagePick1.setDisable(false);
		 DBpic[1] = null;
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
		 filenameArr[2] = null;
		 ImagePick2.setImage(pic);
		 Xbtn3.setDisable(true);
		 Xbtn3.setVisible(false);
		 
		 ImagePick2.setDisable(false);
		 DBpic[2] = null;
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
		 filenameArr[3] = null;
		 ImagePick3.setImage(pic);
		 Xbtn4.setDisable(true);
		 Xbtn4.setVisible(false);
		 
		 ImagePick3.setDisable(false);
		 DBpic[3] = null;
		 
	}
	
	
	
	

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
		
		Object result =  reply.getResult();
		
		
		if(result instanceof Result)
		{
			
		if ((Result)result == Result.ERROR){
			
			ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
				System.exit(1);
		}
		
		
		}
		
		if (reply.getCommand() == Command.CREATE_EXAMINATION )
		{
			
			
			
			ExaminationController.Curr_Ref.setCode(((int)result));
			onBackButtonClick();
			ClientConnectionController.clientConnect.userInterface.get(1).displayMessage ("CREATE", "Examination Successfully CREATED");
		}
		
		
		
		if (reply.getCommand() == Command.CREATE_EXAMINATION_VIEW )
		{
			
			Examination exam = (Examination)result;
			ExamTextArea.setText(exam.getDetails());
			
			initializePictures(exam);
			

			
			
			
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
			 
			
			 FileNameToArrayList();
			 
			
		 Examination exam = new Examination();
		 exam.setRef_id(ExaminationController.Curr_Ref.getRefNum());
         exam.setDetails(ExamTextArea.getText());
         exam.setPictures(pictures);
		 request = new Request(Command.CREATE_EXAMINATION, exam);
		
		 
		 
		 }
		 else
		 {
			 
			 FileNameToArrayList();
			
			 
			 for(int i=0 ; i <4 ;i++)
				 if( DBpic[i] != null)
				 {
					 pictures.add(DBpic[i]);
					 
				 }
			 
			
			this.exam.setPictures(pictures);
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
	  
	  public byte[] convertFileToBytes(String filename)
	  {
		  FileInputStream fileinputstream = null;
		  File file = new File(filename);
		  byte[] bfile = new byte[(int)file.length()];
		  try{
			  fileinputstream = new FileInputStream(file);
			  fileinputstream.read(bfile);
			  fileinputstream.close();
		  }
		  
		  catch(Exception e){
			  bfile = null;
		  }
		return bfile;  
	  }
	  
	  public void FileNameToArrayList()
	  {
		  for(int i=0;i<4;i++)
		  {
			  if( filenameArr[i] != null)
				  pictures.add(convertFileToBytes(filenameArr[i]));
		  }
	  }
	  
	 
	  
	  public Image convertBytesToImage(byte[] pic)
	  {
		  BufferedImage img = null;
			
			try {
				img = ImageIO.read(new ByteArrayInputStream(pic));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WritableImage wr = null;
	        if (img != null) {
	            wr = new WritableImage(img.getWidth(), img.getHeight());
	            PixelWriter pw = wr.getPixelWriter();
	            for (int x = 0; x < img.getWidth(); x++) {
	                for (int y = 0; y < img.getHeight(); y++) {
	                    pw.setArgb(x, y, img.getRGB(x, y));
	                }
	            }
	        }
	 
	        ImageView imView = new ImageView(wr);
	      
	        Image im = imView.getImage();
	       
	        
	        return im;
	        
	  }
	  
	  public void initializePictures(Examination exam)
	  {
		  int num = exam.getPictures().size();  //check how many picture loaded
			
			if(num == 1)
			{
			ImagePick.setImage(convertBytesToImage(exam.getPictures().get(0)));
			DBpic[0] = exam.getPictures().get(0);
			//resizeing
			
			ImagePick.setFitWidth(100);
			ImagePick.setFitHeight(100);
			ImagePick.setPreserveRatio(false);
			ImagePick.setSmooth(false);
			
			ImagePick.setDisable(true);
			
			//set Close Button visible
			
			 Xbtn1.setDisable(false);
  		 Xbtn1.setVisible(true);
			
			}
			
			if(num == 2)
			{
				ImagePick.setImage(convertBytesToImage(exam.getPictures().get(0)));
				ImagePick1.setImage(convertBytesToImage(exam.getPictures().get(1)));
				DBpic[0] = exam.getPictures().get(0);
				DBpic[1] = exam.getPictures().get(1);
				
				
				
				//resizeing
				
				ImagePick.setFitWidth(100);
				ImagePick.setFitHeight(100);
				ImagePick.setPreserveRatio(false);
				ImagePick.setSmooth(false);
				
				ImagePick1.setFitWidth(100);
				ImagePick1.setFitHeight(100);
				ImagePick1.setPreserveRatio(false);
				ImagePick1.setSmooth(false);
				
				ImagePick.setDisable(true);
				ImagePick1.setDisable(true);
				
				//set Close Button visible
				
				 Xbtn1.setDisable(false);
	    		 Xbtn1.setVisible(true);
	    		 
	    		 Xbtn2.setDisable(false);
	    		 Xbtn2.setVisible(true);
				
				
			}
			
			if(num == 3)
			{
				ImagePick.setImage(convertBytesToImage(exam.getPictures().get(0)));
				ImagePick1.setImage(convertBytesToImage(exam.getPictures().get(1)));
				ImagePick2.setImage(convertBytesToImage(exam.getPictures().get(2)));
				DBpic[0] = exam.getPictures().get(0);
				DBpic[1] = exam.getPictures().get(1);
				DBpic[2] = exam.getPictures().get(2);
				
				//resizeing
				
				ImagePick.setFitWidth(100);
				ImagePick.setFitHeight(100);
				ImagePick.setPreserveRatio(false);
				ImagePick.setSmooth(false);
				
				ImagePick1.setFitWidth(100);
				ImagePick1.setFitHeight(100);
				ImagePick1.setPreserveRatio(false);
				ImagePick1.setSmooth(false);
				
				ImagePick2.setFitWidth(100);
				ImagePick2.setFitHeight(100);
				ImagePick2.setPreserveRatio(false);
				ImagePick2.setSmooth(false);
				
				ImagePick.setDisable(true);
				ImagePick1.setDisable(true);
				ImagePick2.setDisable(true);
				
				//set Close Button visible
				
				 Xbtn1.setDisable(false);
	    		 Xbtn1.setVisible(true);
	    		 
	    		 Xbtn2.setDisable(false);
	    		 Xbtn2.setVisible(true);
	    		 
	    		 Xbtn3.setDisable(false);
	    		 Xbtn3.setVisible(true);
	    		 
	    		 
				
				
			}
			
			if(num == 4)
			{
				ImagePick.setImage(convertBytesToImage(exam.getPictures().get(0)));
				ImagePick1.setImage(convertBytesToImage(exam.getPictures().get(1)));
				ImagePick2.setImage(convertBytesToImage(exam.getPictures().get(2)));
				ImagePick3.setImage(convertBytesToImage(exam.getPictures().get(3)));
				DBpic[0] = exam.getPictures().get(0);
				DBpic[1] = exam.getPictures().get(1);
				DBpic[2] = exam.getPictures().get(2);
				DBpic[3] = exam.getPictures().get(3);
				
				//resizeing
				
				ImagePick.setFitWidth(100);
				ImagePick.setFitHeight(100);
				ImagePick.setPreserveRatio(false);
				ImagePick.setSmooth(false);
				
				ImagePick1.setFitWidth(100);
				ImagePick1.setFitHeight(100);
				ImagePick1.setPreserveRatio(false);
				ImagePick1.setSmooth(false);
				
				ImagePick2.setFitWidth(100);
				ImagePick2.setFitHeight(100);
				ImagePick2.setPreserveRatio(false);
				ImagePick2.setSmooth(false);
				
				ImagePick3.setFitWidth(100);
				ImagePick3.setFitHeight(100);
				ImagePick3.setPreserveRatio(false);
				ImagePick3.setSmooth(false);
				
				ImagePick.setDisable(true);
				ImagePick1.setDisable(true);
				ImagePick2.setDisable(true);
				ImagePick3.setDisable(true);
				
				//set Close Button visible
				
				 Xbtn1.setDisable(false);
	    		 Xbtn1.setVisible(true);
	    		 
	    		 Xbtn2.setDisable(false);
	    		 Xbtn2.setVisible(true);
	    		 
	    		 Xbtn3.setDisable(false);
	    		 Xbtn3.setVisible(true);
	    		 
	    		 Xbtn4.setDisable(false);
	    		 Xbtn4.setVisible(true);
				
			}
			
		
	  }
	  

}
