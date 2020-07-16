package com.QUILEIA.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.services.OfficerService;

@RestController
public class ServiceController 
{
	public static final String BASE_URL = "/api/v1/officers";
	
	private final OfficerService officersService;

	public ServiceController(OfficerService officersService) 
	{
		super();
		this.officersService = officersService;
	}
	
	@RequestMapping(value = BASE_URL)
	public Iterable<Officer> getAllOfficers()
	{
		return officersService.findAllOfficers();
	}
	
	@RequestMapping(value = BASE_URL + "/{id}")
	public Officer getOfficerById(@PathVariable String id)
	{
		return officersService.findOfficerById(id);
	}
	
}
