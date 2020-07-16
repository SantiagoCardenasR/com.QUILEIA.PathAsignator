package com.QUILEIA.app.services;

import java.util.List;

import com.QUILEIA.app.model.Officer;

public interface OfficerService 
{
	public Officer findOfficerById(String pId);
	
	public Iterable<Officer> findAllOfficers();
}
