package client.entity;

import java.io.Serializable;
import java.util.Calendar;
// TODO: Auto-generated Javadoc

/**
 * Client class defines the client information.
 *
 * @author YAKIR
 */
public class Client extends Person implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The join date. */
	//Class Members
	private Calendar joinDate;
	
	/** The clinic. */
	private String clinic;
	
	/** The status. */
	private boolean status;
	
	/** The medical file. */
	private MedicalFile medicalFile;
	
	
	
	/**
	 * Instantiates a new client.
	 *
	 * @param name the name
	 * @param family the family
	 * @param id the id
	 * @param address the address
	 * @param phone the phone
	 * @param clinic the clinic
	 * @param email the email
	 */
	//Constructors
	public Client(String name, String family, String id, String address, String phone, String clinic, String email){
		this.name = name;
		this.familyName = family;
		this.id = id;
		this.email = email;
		this.address= address;
		this.phone = phone;
		this.clinic = clinic;
		this.joinDate = Calendar.getInstance();
	}
	
	/**
	 * Gets the join date.
	 *
	 * @return the join date
	 */
	//Class Properties
	public Calendar getJoinDate() {
		return joinDate;
	}

	/**
	 * Gets the clinic.
	 *
	 * @return the clinic
	 */
	public String getClinic() {
		return clinic;
	}
	
	/**
	 * Sets the clinic.
	 *
	 * @param clinic the new clinic
	 */
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	
	/**
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Gets the medical file.
	 *
	 * @return the medical file
	 */
	public MedicalFile getMedicalFile() {
		return medicalFile;
	}

	/**
	 * Sets the medical file.
	 *
	 * @param medicalFile the new medical file
	 */
	public void setMedicalFile(MedicalFile medicalFile) {
		this.medicalFile = medicalFile;
	}

	
}