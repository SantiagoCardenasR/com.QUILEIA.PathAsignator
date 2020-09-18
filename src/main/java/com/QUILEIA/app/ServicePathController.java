package com.QUILEIA.app;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.services.PathService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ServicePathController {
	
	public static final String PATH = "paths";
	private final PathService pathService;
	
	public ServicePathController(PathService pPathService) {
		super();
		pathService = pPathService;
	}

	@GetMapping("/paths")
	public Iterable<Path> pathsList() {
		return pathService.findAllPaths();
	}
	
	@PostMapping(value = "/addPath", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public Iterable<Path> addPath(@RequestBody Path pathData) {
		if(pathService.addPath(pathData.getId(), pathData.getType(), pathData.getIsStreetOrKr(), pathData.getNumber(), pathData.getTraffic())) { 
			return pathService.findAllPaths(); 
		}
		return pathService.findAllPaths();
	}
	
	@PutMapping(value = "/editPath", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public Path editPath(@RequestBody Path pathData) {
		pathService.editPath(pathData.getId(), pathData.getType(), pathData.getIsStreetOrKr(), pathData.getNumber(), pathData.getTraffic());
		return pathService.findPathById(pathData.getId());
	}
	
	@RequestMapping(value = "/deletePath/{pId}", method = RequestMethod.GET)
	public Iterable<Path> deltePath(@PathVariable int pId) {
		pathService.deletePath(pId);
		return pathService.findAllPaths();
	}
}