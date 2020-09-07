package com.QUILEIA.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "path")
public class Path {
	@Id
	@Column(name = "cod_path")
	private int id;
	private String type;
	private String isStreetOrKr;
	private int number;
	private int traffic; //The Traffic can take place between 0 and 100
	
	public Path() {
	}
	
	public Path(int pId, String pType,String pIsStreetOrKr, int pNumber, int pTraffic) {
		id = pId;
		type = pType;
		isStreetOrKr = pIsStreetOrKr;
		number = pNumber;
		traffic = pTraffic;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsStreetOrKr() {
		return isStreetOrKr;
	}

	public void setIsStreetOrKr(String isStreetOrKr) {
		this.isStreetOrKr = isStreetOrKr;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}

	@Override
	public String toString() {
		return "Path [id=" + id + ", type=" + type + ", isStreetOrKr=" + isStreetOrKr + ", number=" + number
				+ ", traffic=" + traffic + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Path other = (Path) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
