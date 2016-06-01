package client.entity;

import java.io.Serializable;

/**
 * Person class defines the persons information
 * @author YAKIR
 *
 */
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	//Class Members
	protected String name;
	protected String familyName;
	protected String id;
	protected String email;
	protected String address;
	protected String phone;
	
	
	//Class Properties
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
}