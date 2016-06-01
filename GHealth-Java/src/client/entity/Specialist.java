package client.entity;


/**
 * Specialist class defines the specialists information including 
 * his branch name and address
 * the class extends Person
 */

public class Specialist extends Person{

	private static final long serialVersionUID = 1L;
	
	//Class Members
	private String speciality;
	private String branchName;
	private String branchAddress;

	//Constructors
	public Specialist(String id, String name, String family, String speciality, String branchName, String branchAddress) {
		this.id = id;
		this.name = name;
		this.familyName = family;
		this.speciality = speciality;
		this.branchName = branchName;
		this.branchAddress = branchAddress;

	}
	
	//Class Properties
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	
}