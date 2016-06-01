package client.entity;

import java.io.Serializable;
import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * Appointment class defines an appointments entity in GHealth System.
 *
 * @author shays
 */
public class Appointment  implements Serializable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The appointment id. */
	//class variables
	private int appointmentID;
	
	/** The date. */
	private Date date;
	
	/** The time. */
	private int time;
	
	/** The invite date. */
	private String inviteDate;
	
	/** The start time. */
	private String startTime;
	
	/** The review details. */
	private String reviewDetails;
	
	/** The price. */
	private int price;
	
	/** The missed status. */
	private boolean missedStatus;
	
	/** The specialist id. */
	private int specialistID;
	
	/** The client id. */
	private int clientID;
	
	/** The specialist name. */
	private String specialistName;
	
	/** The branch name. */
	private String branchName;
	
	/** The time string. */
	private String timeString;
	
	/** The date string. */
	private String dateString;
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	//class properties
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * Gets the invite date.
	 *
	 * @return the invite date
	 */
	public String getInviteDate() {
		return inviteDate;
	}
	
	/**
	 * Sets the invite date.
	 *
	 * @param inviteDate the new invite date
	 */
	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets the review details.
	 *
	 * @return the review details
	 */
	public String getReviewDetails() {
		return reviewDetails;
	}
	
	/**
	 * Sets the review details.
	 *
	 * @param reviewDetails the new review details
	 */
	public void setReviewDetails(String reviewDetails) {
		this.reviewDetails = reviewDetails;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Checks if is missed status.
	 *
	 * @return true, if is missed status
	 */
	public boolean isMissedStatus() {
		return missedStatus;
	}
	
	/**
	 * Sets the missed status.
	 *
	 * @param missedStatus the new missed status
	 */
	public void setMissedStatus(boolean missedStatus) {
		this.missedStatus = missedStatus;
	}

	/**
	 * Gets the specialist id.
	 *
	 * @return the specialist id
	 */
	public int getSpecialistID() {
		return specialistID;
	}

	/**
	 * Sets the specialist id.
	 *
	 * @param specialistID the new specialist id
	 */
	public void setSpecialistID(int specialistID) {
		this.specialistID = specialistID;
	}

	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public int getClientID() {
		return clientID;
	}



	/**
	 * Sets the client id.
	 *
	 * @param clientID the new client id
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	/**
	 * Gets the branch name.
	 *
	 * @return the branch name
	 */
	public String getBranchName() {
		return branchName;
	}
	
	/**
	 * Sets the branch name.
	 *
	 * @param branchName the new branch name
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	 * Gets the appointment id.
	 *
	 * @return the appointment id
	 */
	public int getAppointmentID() {
		return appointmentID;
	}
	
	/**
	 * Sets the appointment id.
	 *
	 * @param appointmentID the new appointment id
	 */
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	
	/**
	 * Gets the time string.
	 *
	 * @return the time string
	 */
	public String getTimeString() {
		return timeString;
	}
	
	/**
	 * Sets the time string.
	 *
	 * @param timeString the new time string
	 */
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	
	/**
	 * Gets the date string.
	 *
	 * @return the date string
	 */
	public String getDateString() {
		return dateString;
	}
	
	/**
	 * Sets the date string.
	 *
	 * @param dateString the new date string
	 */
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

}