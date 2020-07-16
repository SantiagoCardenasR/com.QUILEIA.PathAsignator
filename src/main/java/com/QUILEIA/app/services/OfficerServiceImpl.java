package com.QUILEIA.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.repos.OfficerRepo;

@Service
public class OfficerServiceImpl implements OfficerService
{
	private final OfficerRepo officersRepo;
	
	public OfficerServiceImpl(OfficerRepo officersRepo) 
	{
		super();
		this.officersRepo = officersRepo;
	}

	@Override
	public Officer findOfficerById(String pId) 
	{
		return officersRepo.findById(pId).get();
	}

	@Override
	public Iterable<Officer> findAllOfficers() 
	{
		return officersRepo.findAll();
	}

}
