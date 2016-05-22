package client.entity;

import java.io.Serializable;
import java.util.List;

public class MedicalFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Examination> examinations;
	private List<Appointment> appointments;
	
	
	public List<Examination> getExaminations() {
		return examinations;
	}
	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
	
}