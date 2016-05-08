package client.entity;

import java.io.Serializable;
import java.sql.Date;

public class Appointment  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//class variables
	private int appointmentID;
	private Date date;
	private int time;
	private String inviteDate;
	private String startTime;
	private String reviewDetails;
	private int price;
	private boolean missedStatus;
	private int specialistID;
	private int clientID;
	private String specialistName;
	private String branchName;
	private String TimeString;
		
	
	//class properties
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getInviteDate() {
		return inviteDate;
	}
	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getReviewDetails() {
		return reviewDetails;
	}
	public void setReviewDetails(String reviewDetails) {
		this.reviewDetails = reviewDetails;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isMissedStatus() {
		return missedStatus;
	}
	public void setMissedStatus(boolean missedStatus) {
		this.missedStatus = missedStatus;
	}

	public int getSpecialistID() {
		return specialistID;
	}

	public void setSpecialistID(int specialistID) {
		this.specialistID = specialistID;
	}

	public int getClientID() {
		return clientID;
	}



	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getSpecialistName() {
		return specialistName;
	}
	public void setSpecialistName(String specialistName) {
		this.specialistName = specialistName;
	}
	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public String getTimeString() {
		return TimeString;
	}
	public void setTimeString(String timeString) {
		TimeString = timeString;
	}

}