package com.QUILEIA.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "officer")
public class Officer 
{
	@Id
	@Column(name = "cod_officer")
	private String code;
	private String name;
	private int yearsOfExperience;
	private String transitSecretaryCode;
	private int actualPathId;
	//private Path actualpath;
	
	public Officer()
	{}
	
	public Officer(String pCode, String pName, int pYearsOfExperience, String pTransitSecretaryCode, int pActualPathId)
	{
		name = pName;
		code = pCode;
		yearsOfExperience = pYearsOfExperience;
		transitSecretaryCode = pTransitSecretaryCode;
		actualPathId = pActualPathId;
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

	public int getActualPathId() 
	{
		return actualPathId;
	}

	public void setActualPathId(int actualPathId) 
	{
		this.actualPathId = actualPathId;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public String toString() 
	{
		return "Officer [code=" + code + ", name=" + name + ", yearsOfExperience=" + yearsOfExperience
				+ ", transitSecretaryCode=" + transitSecretaryCode + ", actualPathId=" + actualPathId + "]";
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Officer other = (Officer) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
