package com.QUILEIA.app.model;

public class PathPOJO {
	
	private int id;
	private String type;
	private String isStreetOrKr;
	private int number;
	private int traffic; //The Traffic can take place between 0 and 100
	
	public PathPOJO(int pId, String pType,String pIsStreetOrKr, int pNumber, int pTraffic) {
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
}
