package Fixtures;

import java.text.SimpleDateFormat;

import client.control.ClientConnectionController;
import client.control.CreateAppointmentController;
import client.entity.Appointment;
import fit.ActionFixture;


public class CreateAppointmentTest extends ActionFixture{

	private Appointment appointment = new Appointment();
	private CreateAppointmentController appCtrl = new CreateAppointmentController();

	public void setAppointmentDate(String dateStr){
		SimpleDateFormat sdf;
		java.util.Date date = null;
		
		try{
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateStr);
			java.sql.Date parsed = new java.sql.Date(date.getTime());
			appointment.setDate(parsed);
			
		}catch(Exception e){
		}
	}
	
	
	public String getDate(){
		return appointment.getDate().toString();
	}
	
	public void setTime(int time){
		appointment.setTime(time);
	}
	
	public int getTime(){
		return appointment.getTime();
	}

	public void setSpecialistId(int id){
		appointment.setSpecialistID(id);
	}
	
	public int getSpecialistId(){
		return appointment.getSpecialistID();
	}
	
	public void setClientId(int id){
		appointment.setClientID(id);
	}
	
	
	public int getClientId(){
		return appointment.getClientID();
	}
	
	
	public String createAppointment(){
		ClientConnectionController.connectToServer(ClientConnectionController.DEFAULT_IP, ClientConnectionController.DEFAULT_PORT);
		appCtrl.createAppointment(appointment);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return appCtrl.returnMsg;

	}
	
}
