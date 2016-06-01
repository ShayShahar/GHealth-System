package client.entity;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Person class defines the persons information.
 *
 * @author YAKIR
 */
public class Person implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The name. */
	//Class Members
	protected String name;
	
	/** The family name. */
	protected String familyName;
	
	/** The id. */
	protected String id;
	
	/** The email. */
	protected String email;
	
	/** The address. */
	protected String address;
	
	/** The phone. */
	protected String phone;
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	//Class Properties
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the family name.
	 *
	 * @return the family name
	 */
	public String getFamilyName() {
		return familyName;
	}
	
	/**
	 * Sets the family name.
	 *
	 * @param familyName the new family name
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
}