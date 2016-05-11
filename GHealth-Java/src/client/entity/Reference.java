package client.entity;

import java.io.Serializable;
import java.util.Date;

public class Reference implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private enum Urgency {LOW, MEDIUM, HIGH, CRITICAL};
	
	//class variables
	private int RefNum;
	private int cid;
	private int sid;
	private Date date;
	private String comments;
	private int code;
	private Urgency urgency;
	private int status;
	private String Type;
	
	
	//class properties
	public int getCId() {
		return cid;
	}
	public void setCId(int cid) {
		this.cid = cid;
	}
	public int getSId() {
		return sid;
	}
	public void setSId(int sid) {
		this.sid = sid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getUrgency() {
		if(urgency == Urgency.LOW)
		return "LOW";
		if(urgency == Urgency.MEDIUM)
		 return "MEDIUM";
		if(urgency == Urgency.HIGH)
			 return "HIGH";
		if(urgency == Urgency.CRITICAL)
			 return "CRITICAL";
		return null;
	}
	public void setUrgency(String urgency) {
		if(urgency.equals("LOW"))
			this.urgency = Urgency.LOW;
		else if(urgency.equals("MEDIUM"))
			this.urgency = Urgency.MEDIUM;
		else if(urgency.equals("HIGH"))
			this.urgency = Urgency.HIGH;
		else if(urgency.equals("CRITICAL"))
			this.urgency = Urgency.CRITICAL;
		else this.urgency = null;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus()
	{
		return status;
	}
	public int getRefNum() {
		return RefNum;
	}
	public void setRefNum(int refNum) {
		RefNum = refNum;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	
	
}