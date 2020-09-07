package com.QUILEIA.app.services;

import com.QUILEIA.app.model.Officer;

public interface OfficerService {
	public Officer findOfficerById(String pId);
	
	public Iterable<Officer> findAllOfficers();
	
	public boolean addOfficer(String pId, String pName,String pLastName, int pYoex, String pTsc, int pIdPath);
	
	public void editOfficer(String pId, String pName,String pLastName, int pYoex, String pTsc, int pIdPath);
	
	public void deleteOfficer(String pId);
}
