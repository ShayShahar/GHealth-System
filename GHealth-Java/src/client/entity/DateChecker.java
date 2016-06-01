package client.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  DateChecker class defines the appointment's dates
 * @author YAKIR
 *
 */
public class DateChecker implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Class Members
	private Date date;
	private String appointments;
	
	//Class Properties
	public String getAppointments() {
		return appointments;
	}
	public void setAppointments(String appointments) {
		this.appointments = appointments;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
