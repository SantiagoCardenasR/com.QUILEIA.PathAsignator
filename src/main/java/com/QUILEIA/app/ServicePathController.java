package com.QUILEIA.app;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.services.PathService;

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
	
	@RequestMapping(value = "/addPath/{pId},{pTipoVia},{pStrOrKr},{pNumber},{pTraffic}", method = RequestMethod.POST)
	public Iterable<Path> addPath(@PathVariable int pId, @PathVariable String pTipoVia, @PathVariable String pStrOrKr, @PathVariable int pNumber, @PathVariable int pTraffic) {
		if(pathService.addPath(pId, pTipoVia, pStrOrKr, pNumber, pTraffic)) { 
			return pathService.findAllPaths(); 
		}
		return pathService.findAllPaths();
	}
	
	@RequestMapping(value = "/editPath/{pId},{pTipoVia},{pStrOrKr},{pNumber},{pTraffic}", method = RequestMethod.POST)
	public Path editPath(@PathVariable int pId, @PathVariable String pTipoVia, @PathVariable String pStrOrKr, @PathVariable int pNumber, @PathVariable int pTraffic) {
		pathService.editPath(pId, pTipoVia, pStrOrKr, pNumber, pTraffic);
		return pathService.findPathById(pId);
	}
	
	@RequestMapping(value = "/deletePath/{pId}", method = RequestMethod.GET)
	public Iterable<Path> deltePath(@PathVariable int pId) {
		pathService.deletePath(pId);
		return pathService.findAllPaths();
	}
}