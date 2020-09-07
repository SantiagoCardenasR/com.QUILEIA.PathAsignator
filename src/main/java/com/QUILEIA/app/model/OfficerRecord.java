package com.QUILEIA.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OfficerRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String officerName;
	private String pathName;
	private String date;
	private String officerId;
	private int pathId;
	
	public OfficerRecord(String officerName, String pathName, String date, String officerId, int pathId) {
		super();
		this.officerName = officerName;
		this.pathName = pathName;
		this.date = date;
		this.officerId = officerId;
		this.pathId = pathId;
	}

	public OfficerRecord() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public int getPathId() {
		return pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}
	
	@Override
	public String toString() {
		return "OfficerRecord [id=" + id + ", officerName=" + officerName + ", pathName=" + pathName + ", date=" + date
				+ ", officerId=" + officerId + ", pathId=" + pathId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((officerId == null) ? 0 : officerId.hashCode());
		result = prime * result + ((officerName == null) ? 0 : officerName.hashCode());
		result = prime * result + pathId;
		result = prime * result + ((pathName == null) ? 0 : pathName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficerRecord other = (OfficerRecord) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (officerId == null) {
			if (other.officerId != null)
				return false;
		} else if (!officerId.equals(other.officerId))
			return false;
		if (officerName == null) {
			if (other.officerName != null)
				return false;
		} else if (!officerName.equals(other.officerName))
			return false;
		if (pathId != other.pathId)
			return false;
		if (pathName == null) {
			if (other.pathName != null)
				return false;
		} else if (!pathName.equals(other.pathName))
			return false;
		return true;
	}
}
