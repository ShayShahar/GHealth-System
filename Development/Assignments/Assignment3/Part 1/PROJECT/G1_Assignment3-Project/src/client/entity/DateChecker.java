package client.entity;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 *  DateChecker class defines the appointment's dates.
 *
 * @author YAKIR
 */
public class DateChecker implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The date. */
	//Class Members
	private Date date;
	
	/** The appointments. */
	private String appointments;
	
	/**
	 * Gets the appointments.
	 *
	 * @return the appointments
	 */
	//Class Properties
	public String getAppointments() {
		return appointments;
	}
	
	/**
	 * Sets the appointments.
	 *
	 * @param appointments the new appointments
	 */
	public void setAppointments(String appointments) {
		this.appointments = appointments;
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
	
}
