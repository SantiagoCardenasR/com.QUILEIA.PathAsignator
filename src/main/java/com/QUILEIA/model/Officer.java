package com.QUILEIA.model;

public class Officer 
{
	private String code;
	private String name;
	private int yearsOfExperience;
	private String transitSecretaryCode;
	private int actualPath;
	
	public Officer(String pCode, String pName, int pYearsOfExperience, String pTransitSecretaryCode, int pActualPath)
	{
		name = pName;
		code = pCode;
		yearsOfExperience = pYearsOfExperience;
		transitSecretaryCode = pTransitSecretaryCode;
		actualPath = pActualPath;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getCode() 
	{
		return code;
	}

	public void setCode(String code) 
	{
		this.code = code;
	}

	public int getYearsOfExperience() 
	{
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) 
	{
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getTransitSecretaryCode() 
	{
		return transitSecretaryCode;
	}

	public void setTransitSecretaryCode(String transitSecretaryCode) 
	{
		this.transitSecretaryCode = transitSecretaryCode;
	}

	public int getActualPath() 
	{
		return actualPath;
	}

	public void setActualPath(int actualPath) 
	{
		this.actualPath = actualPath;
	}
	
	

}
