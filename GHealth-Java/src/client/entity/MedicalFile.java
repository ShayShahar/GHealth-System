package client.entity;

import java.io.Serializable;

public class MedicalFile implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String date;
	private String type;
	private String specialization;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	
}