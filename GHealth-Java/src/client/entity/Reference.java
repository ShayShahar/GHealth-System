package client.entity;

import java.io.Serializable;
import java.util.Date;

public class Reference implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private enum Urgency {Low, Normal, Critical};
	
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
	
	
	public Reference()
	{
		code = 0;
	}
	
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
		if(urgency == Urgency.Low)
		return "LOW";
		if(urgency == Urgency.Normal)
		 return "NORMAL";
		if(urgency == Urgency.Critical)
			 return "CRITICAL";
		return null;
	}
	public void setUrgency(String urgency) {
		if(urgency.equals("LOW"))
			this.urgency = Urgency.Low;
		else if(urgency.equals("NORMAL"))
			this.urgency = Urgency.Normal;
		else if(urgency.equals("CRITICAL"))
			this.urgency = Urgency.Critical;
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