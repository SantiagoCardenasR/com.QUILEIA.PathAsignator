package com.QUILEIA.app;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.services.OfficerService;

@CrossOrigin(origins = "http://localhost:4200")
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
	
	@PostMapping(value = "/addOfficer", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<User> addOfficer(@RequestBody Officer officerDetails) {
		if(officersService.addOfficer(officerDetails.getCode(), officerDetails.getName(), officerDetails.getLastName(), officerDetails.getYearsOfExperience(), officerDetails.getTransitSecretaryCode(), officerDetails.getActualPathId())) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@PutMapping(value = "/editOfficer", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public  Officer editOfficer(@RequestBody Officer officerDetails) {
		if(officersService.editOfficer(officerDetails.getCode(), officerDetails.getName(), officerDetails.getLastName(), officerDetails.getYearsOfExperience(), officerDetails.getTransitSecretaryCode(), officerDetails.getActualPathId())) {
			return officersService.findOfficerById(officerDetails.getCode());
		} else {
			return null;
		}
	}
	
	@PostMapping(value = "/deleteOfficer/")
	public Iterable<Officer> deleteOfficer(@RequestBody String pId) {
		officersService.deleteOfficer(pId);
		return officersService.findAllOfficers();
	}
	
	@GetMapping(value = BASE_URL + "/{id}")
	public Officer getOfficerById(@PathVariable String id) {
		return officersService.findOfficerById(id);
	}
	
}
