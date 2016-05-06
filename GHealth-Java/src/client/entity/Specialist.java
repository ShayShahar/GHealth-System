package client.entity;

public class Specialist extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//class variables
	private String speciality;
	private Branch branch;
	private String branchName;
	private String branchAddress;

	public Specialist(String id, String name, String family, String speciality, Branch branch) {
		this.id = id;
		this.name = name;
		this.familyName = family;
		this.speciality = speciality;
		this.branch = branch;
		setBranchName(branch.getName());
		setBranchAddress(branch.getAddress());
	}
	
	//class properties
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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