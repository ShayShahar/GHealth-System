package client.entity;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Reference class defines the References information.
 *
 * @author YAKIR
 */
public class Reference implements Serializable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Enum Urgency.
	 */
	private enum Urgency {
/** The Low. */
Low, 
 /** The Normal. */
 Normal, 
 /** The Critical. */
 Critical};
	
	/** The Ref num. */
	//Class Members
	private int RefNum;
	
	/** The cid. */
	private int cid;
	
	/** The sid. */
	private int sid;
	
	/** The date. */
	private Date date;
	
	/** The comments. */
	private String comments;
	
	/** The code. */
	private int code;
	
	/** The urgency. */
	private Urgency urgency;
	
	/** The status. */
	private int status;
	
	/** The Type. */
	private String Type;
	
	/**
	 * Instantiates a new reference.
	 */
	//Constructors
	public Reference()
	{
		code = 0;
	}
	
	/**
	 * Gets the c id.
	 *
	 * @return the c id
	 */
	//Class Properties
	public int getCId() {
		return cid;
	}
	
	/**
	 * Sets the c id.
	 *
	 * @param cid the new c id
	 */
	public void setCId(int cid) {
		this.cid = cid;
	}
	
	/**
	 * Gets the s id.
	 *
	 * @return the s id
	 */
	public int getSId() {
		return sid;
	}
	
	/**
	 * Sets the s id.
	 *
	 * @param sid the new s id
	 */
	public void setSId(int sid) {
		this.sid = sid;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
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
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * Gets the urgency.
	 *
	 * @return the urgency
	 */
	//get the urgency level of the reference
	public String getUrgency() {
		if(urgency == Urgency.Low)
		return "LOW";
		if(urgency == Urgency.Normal)
		 return "NORMAL";
		if(urgency == Urgency.Critical)
			 return "CRITICAL";
		return null;
	}
	
	/**
	 * Sets the urgency.
	 *
	 * @param urgency the new urgency
	 */
	//set the urgency level of the reference
	public void setUrgency(String urgency) {
		if(urgency.equals("LOW"))
			this.urgency = Urgency.Low;
		else if(urgency.equals("NORMAL"))
			this.urgency = Urgency.Normal;
		else if(urgency.equals("CRITICAL"))
			this.urgency = Urgency.Critical;
		else this.urgency = null;
	}
	
	/**
	 * Checks if is status.
	 *
	 * @return the int
	 */
	public int isStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus()
	{
		return status;
	}
	
	/**
	 * Gets the ref num.
	 *
	 * @return the ref num
	 */
	public int getRefNum() {
		return RefNum;
	}
	
	/**
	 * Sets the ref num.
	 *
	 * @param refNum the new ref num
	 */
	public void setRefNum(int refNum) {
		RefNum = refNum;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		Type = type;
	}
	
	
	
}