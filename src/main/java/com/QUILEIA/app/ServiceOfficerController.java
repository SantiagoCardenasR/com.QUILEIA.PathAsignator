package com.QUILEIA.app;

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
import com.QUILEIA.app.model.OfficerPOJO;
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
	public ResponseEntity<Iterable<Officer>> addOfficer(@RequestBody OfficerPOJO officerDetails) {
		if(officersService.addOfficer(officerDetails.getCode(), officerDetails.getName(), officerDetails.getLastName(), officerDetails.getYearsOfExperience(), officerDetails.getTransitSecretaryCode(), officerDetails.getActualPathId())) {
			return new ResponseEntity<Iterable<Officer>>(officersService.findAllOfficers(), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@PutMapping(value = "/editOfficer", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public  ResponseEntity<Iterable<Officer>> editOfficer(@RequestBody OfficerPOJO officerDetails) {
		if(officersService.editOfficer(officerDetails.getCode(), officerDetails.getName(), officerDetails.getLastName(), officerDetails.getYearsOfExperience(), officerDetails.getTransitSecretaryCode(), officerDetails.getActualPathId()) == true) {
			return new ResponseEntity<Iterable<Officer>>(officersService.findAllOfficers(), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Iterable<Officer>>(officersService.findAllOfficers(), HttpStatus.BAD_GATEWAY);
		}
	}
	
	@DeleteMapping(value = "/deleteOfficer/{pId}")
	public ResponseEntity<Iterable<Officer>> deleteOfficer(@PathVariable String pId) {
		officersService.deleteOfficer(pId);
		return new ResponseEntity<Iterable<Officer>>(officersService.findAllOfficers(), HttpStatus.OK);
	}
	
	@GetMapping(value = BASE_URL + "/{id}")
	public Officer getOfficerById(@PathVariable String id) {
		return officersService.findOfficerById(id);
	}
	
}
