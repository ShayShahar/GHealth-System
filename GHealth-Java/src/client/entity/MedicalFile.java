package client.entity;

import java.io.Serializable;
// TODO: Auto-generated Javadoc

/**
 * MedicalFile class defines the Medical Files information.
 *
 * @author YAKIR
 */
public class MedicalFile implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The name. */
	//Class Members
	private String name;
	
	/** The date. */
	private String date;
	
	/** The type. */
	private String type;
	
	/** The specialization. */
	private String specialization;
	
	/** The id. */
	private String id;
	
	/** The info. */
	private String info;
	
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	//Class Properties
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
	 * Gets the name.
	 *
	 * @return the name
	 */
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
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the specialization.
	 *
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}
	
	/**
	 * Sets the specialization.
	 *
	 * @param specialization the new specialization
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	/**
	 * Sets the info.
	 *
	 * @param info the new info
	 */
	public void setInfo(String info) {
		this.info = info;
		
	}
	
	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public String getInfo() {
		return info;
		
	}
	
	
}