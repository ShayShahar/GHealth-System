package client.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Client extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//Members
	private Calendar joinDate;
	private String clinic;
	private boolean status;
	private MedicalFile medicalFile;
	
	
	//Constructors
	public Client() {
		medicalFile = new MedicalFile();
	}
	
	public Client(String name, String family, String id, String address, String phone, String clinic, String email){
		this.name = name;
		this.familyName = family;
		this.id = id;
		this.email = email;
		this.address= address;
		this.phone = phone;
		this.clinic = clinic;
		this.joinDate = Calendar.getInstance();
		medicalFile = new MedicalFile();
	}
	
	//Properties
	public Calendar getJoinDate() {
		return joinDate;
	}

	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public MedicalFile getMedicalFile() {
		return medicalFile;
	}

	public void setMedicalFile(MedicalFile medicalFile) {
		this.medicalFile = medicalFile;
	}

	
}