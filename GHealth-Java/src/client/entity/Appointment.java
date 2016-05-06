package client.entity;

import java.io.Serializable;

public class Appointment  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//class variables
	private String date;
	private String time;
	private String inviteDate;
	private String startTime;
	private String reviewDetails;
	private int price;
	private boolean missedStatus;
	
	
	//class constructors
	public Appointment(){}
	
	
	
	//class properties
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
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

}