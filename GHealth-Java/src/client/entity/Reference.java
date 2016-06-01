package client.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Reference class defines the References information
 * @author YAKIR
 *
 */
public class Reference implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private enum Urgency {Low, Normal, Critical};
	
	//Class Members
	private int RefNum;
	private int cid;
	private int sid;
	private Date date;
	private String comments;
	private int code;
	private Urgency urgency;
	private int status;
	private String Type;
	
	//Constructors
	public Reference()
	{
		code = 0;
	}
	
	//Class Properties
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
	//get the urgency level of the reference
	public String getUrgency() {
		if(urgency == Urgency.Low)
		return "LOW";
		if(urgency == Urgency.Normal)
		 return "MEDIUM";
		if(urgency == Urgency.Critical)
			 return "CRITICAL";
		return null;
	}
	//set the urgency level of the reference
	public void setUrgency(String urgency) {
		if(urgency.equals("Low"))
			this.urgency = Urgency.Low;
		else if(urgency.equals("Normal"))
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