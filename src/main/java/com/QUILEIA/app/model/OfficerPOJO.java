package com.QUILEIA.app.model;

public class OfficerPOJO {
	private String code;
	private String name;
	private String lastName;
	private int yearsOfExperience;
	private String transitSecretaryCode;
	private int actualPathId;
	private int assignations;
	
	public OfficerPOJO(String pCode, String pName,String pLastName,  int pYearsOfExperience, String pTransitSecretaryCode, int pActualPathId, int pAssignations) {
		name = pName;
		lastName = pLastName;
		code = pCode;
		yearsOfExperience = pYearsOfExperience;
		transitSecretaryCode = pTransitSecretaryCode;
		actualPathId = pActualPathId;
		assignations = pAssignations;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getTransitSecretaryCode() {
		return transitSecretaryCode;
	}

	public void setTransitSecretaryCode(String transitSecretaryCode) {
		this.transitSecretaryCode = transitSecretaryCode;
	}

	public int getActualPathId() {
		return actualPathId;
	}

	public void setActualPathId(int actualPathId) {
		this.actualPathId = actualPathId;
	}
	

	public int getAssignations() {
		return assignations;
	}

	public void setAssignations(int assignations) {
		this.assignations = assignations;
	}
}
