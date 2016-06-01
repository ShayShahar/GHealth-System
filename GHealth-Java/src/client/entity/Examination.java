package client.entity;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Examination class defines the examination's details.
 *
 * @author YAKIR
 */
public class Examination implements Serializable{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Ref_id. */
	//Class Members
	private int id , Ref_id;
	
	/** The date. */
	private String date;
	
	/** The specialist name. */
	private String specialistName;
	
	/** The specialist. */
	private String specialist;
	
	/** The examination name. */
	private String examinationName;
	
	/** The serial. */
	private String serial;
	
	/** The details. */
	private String details;
	
	/** The pictures. */
	private ArrayList<byte[]> pictures;
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	//Class Properties
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * Gets the pictures.
	 *
	 * @return the pictures
	 */
	public ArrayList<byte[]> getPictures() {
		return pictures;
	}
	
	/**
	 * Sets the pictures.
	 *
	 * @param pictures the new pictures
	 */
	public void setPictures(ArrayList<byte[]> pictures) {
		this.pictures = pictures;
	}
	
	/**
	 * Gets the ref_id.
	 *
	 * @return the ref_id
	 */
	public int getRef_id() {
		return Ref_id;
	}
	
	/**
	 * Sets the ref_id.
	 *
	 * @param ref_id the new ref_id
	 */
	public void setRef_id(int ref_id) {
		Ref_id = ref_id;
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
	 * Gets the specialist name.
	 *
	 * @return the specialist name
	 */
	public String getSpecialistName() {
		return specialistName;
	}
	
	/**
	 * Sets the specialist name.
	 *
	 * @param specialistName the new specialist name
	 */
	public void setSpecialistName(String specialistName) {
		this.specialistName = specialistName;
	}
	
	/**
	 * Gets the specialist.
	 *
	 * @return the specialist
	 */
	public String getSpecialist() {
		return specialist;
	}
	
	/**
	 * Sets the specialist.
	 *
	 * @param specialist the new specialist
	 */
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	
	/**
	 * Gets the examination name.
	 *
	 * @return the examination name
	 */
	public String getExaminationName() {
		return examinationName;
	}
	
	/**
	 * Sets the examination name.
	 *
	 * @param examinationName the new examination name
	 */
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
	
	/**
	 * Gets the serial.
	 *
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}
	
	/**
	 * Sets the serial.
	 *
	 * @param serial the new serial
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
}
	
