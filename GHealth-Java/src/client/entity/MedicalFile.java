package client.entity;

import java.io.Serializable;
/**
 * MedicalFile class defines the Medical Files information
 * @author YAKIR
 *
 */
public class MedicalFile implements Serializable{

	private static final long serialVersionUID = 1L;

	
	//Class Members
	private String name;
	private String date;
	private String type;
	private String specialization;
	private String id;
	private String info;
	
	
	
	//Class Properties
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public void setInfo(String info) {
		this.info = info;
		
	}
	
	public String getInfo() {
		return info;
		
	}
	
	
}