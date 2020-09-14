package com.QUILEIA.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.services.OfficerService;

@RestController
public class ServiceOfficerController {
	public static final String BASE_URL = "/officers";
	public static final String OFFICERS = "officers";
	
	private final OfficerService officersService;

	public ServiceOfficerController(OfficerService officersService) {
		super();
		this.officersService = officersService;
	}
	
	@GetMapping(value = BASE_URL)
	public Iterable<Officer> getAllOfficers() {
		return officersService.findAllOfficers();
	}
	
	@RequestMapping(value = "/addOfficer/{pId},{pName},{pLastName},{pYoex},{pTsc},{pIdPath}", method = RequestMethod.POST)
	public Iterable<Officer> addOfficer(@PathVariable String pId, @PathVariable String pName,  @PathVariable String pLastName, @PathVariable int pYoex, @PathVariable String pTsc, @PathVariable int pIdPath) {
		if(officersService.addOfficer(pId, pName, pLastName, pYoex, pTsc, pIdPath)) {
			return officersService.findAllOfficers();
		}
		return officersService.findAllOfficers();
	}
	
	@RequestMapping(value = "/editOfficer/{pId},{pName},{pLastName},{pYoex},{pTsc},{pIdPath}", method = RequestMethod.POST)
	public Officer editOfficer(@PathVariable String pId, @PathVariable String pName, @PathVariable String pLastName , @PathVariable int pYoex, @PathVariable String pTsc, @PathVariable int pIdPath) {	
		officersService.editOfficer(pId, pName, pLastName, pYoex, pTsc, pIdPath);
		return officersService.findOfficerById(pId);
	}
	
	@RequestMapping(value = "/deleteOfficer/{pId}", method = RequestMethod.GET)
	public Iterable<Officer> deleteOfficer(@PathVariable String pId) {
		officersService.deleteOfficer(pId);
		return officersService.findAllOfficers();
	}
	
	@GetMapping(value = BASE_URL + "/{id}")
	public Officer getOfficerById(@PathVariable String id) {
		return officersService.findOfficerById(id);
	}
	
}
