package Fixtures;

import java.util.ArrayList;

import client.control.ClientConnectionController;
import client.control.CreateExaminationController;
import client.entity.Examination;
import fit.ActionFixture;

public class CreateExaminationTest extends ActionFixture{
	
	Examination examination = new Examination();	
	private CreateExaminationController examCtrl = new CreateExaminationController();

	
	public void setDetails(String details){
		examination.setDetails(details);
	}
	
	public String getDetails(){
		return examination.getDetails();
	}
	
	public void setReferenceId(int id){
		examination.setReferenceId(id);
	}
	
	public int getReferenceId(){
		return examination.getReferenceId();
	}
	
	public String createExamination(){
		examination.setPictures(new ArrayList<byte[]>());
		ClientConnectionController.connectToServer(ClientConnectionController.DEFAULT_IP, ClientConnectionController.DEFAULT_PORT);
		examCtrl.createExamination(examination);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return examCtrl.returnMsg;
	}
}
