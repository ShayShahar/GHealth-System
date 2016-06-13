package Fixtures;

import client.entity.Appointment;
import fit.ActionFixture;


public class CreateAppointmentTest extends ActionFixture{

	private Appointment appointment = new Appointment();
	
	public void setClientId (int id){
		appointment.setClientID(id);
	}
	
	public int getClientId(){
		return appointment.getClientID();
	}
	
	public void setAppointmentId(int id){
		appointment.setAppointmentID(id);
	}
	
	public int getAppointmentId(){
		return appointment.getAppointmentID();
	}
	
	public void setDate(String date){
		appointment.setDateString(date);
	}
	
	public String getDate(){
		return appointment.getDateString();
	}
	
	public void setInviteDate(String date){
		appointment.setInviteDate(date);
	}
	
	public String getInviteDate(){
		return appointment.getInviteDate();
	}

	public void setTime(String time){
		appointment.setTimeString(time);
	}
	
	public String getTime(){
		return appointment.getTimeString();
	}
	
	public void setBranch(String branch){
		appointment.setBranchName(branch);
	}
	
	public String getBranch(){
		return appointment.getBranchName();
	}
	
	public void setSpecialistId(int id){
		appointment.setSpecialistID(id);
	}
	
	public int getSpecialistId(){
		return appointment.getSpecialistID();
	}
}
