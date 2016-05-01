package client.entity;

public class Specialist extends User{
	
	//class variables
	private String speciality;
	private String workingDays;
	private String workingHours;
	
	public Specialist(){
		userPrivilege = Privilege.SPECIALIST;
	}
	
	//class properties
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	
}