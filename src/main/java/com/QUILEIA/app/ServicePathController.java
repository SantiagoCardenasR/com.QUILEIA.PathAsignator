package com.QUILEIA.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.services.PathService;

@Controller
public class ServicePathController {
	
	public static final String PATH = "paths";
	private final PathService pathService;
	
	public ServicePathController(PathService pPathService) {
		super();
		pathService = pPathService;
	}
	
	@RequestMapping(value = "/editPath", method = RequestMethod.GET)
	public String editPathPage(@RequestParam(name = "id_path",required = true) int pId, @RequestParam(name = "type_path",required = true) String pTipoVia, @RequestParam(name = "str_or_kr",required = true) String pStrOrKr, @RequestParam(name = "number_path",required = true) int pNumber, @RequestParam(name = "traffic_path",required = true) int pTraffic, Model model) {
		Path pathToEdit = pathService.findPathById(pId);
		model.addAttribute("path",pathToEdit);
		return "editPath";
	}

	@GetMapping("/paths")
	public String pathsList(Model model) {
		model.addAttribute(PATH, pathService.findAllPaths());
		return PATH;
	}
	
	@RequestMapping(value = "/addPath", method = RequestMethod.POST)
	public String addPath(@RequestParam(name = "id_path",required = true) int pId, @RequestParam(name = "type_path",required = true) String pTipoVia, @RequestParam(name = "str_or_kr",required = true) String pStrOrKr, @RequestParam(name = "number_path",required = true) int pNumber, @RequestParam(name = "traffic_path",required = true) int pTraffic, Model model) {
		if(pathService.addPath(pId, pTipoVia, pStrOrKr, pNumber, pTraffic)) {
			model.addAttribute(PATH, pathService.findAllPaths());
		}
		return PATH;
	}
	
	@RequestMapping(value = "/editPath", method = RequestMethod.POST)
	public String editPath(@RequestParam(name = "id_path",required = true) int pId, @RequestParam(name = "type_path",required = true) String pTipoVia, @RequestParam(name = "str_or_kr",required = true) String pStrOrKr, @RequestParam(name = "number_path",required = true) int pNumber, @RequestParam(name = "traffic_path",required = true) int pTraffic, Model model) {
		pathService.editPath(pId, pTipoVia, pStrOrKr, pNumber, pTraffic);
		model.addAttribute(PATH, pathService.findAllPaths());
		return PATH;
	}
	
	@RequestMapping(value = "/deletePath", method = RequestMethod.GET)
	public String deltePath(@RequestParam(name = "id_path",required = true) int pId, Model model) {
		pathService.deletePath(pId);
		model.addAttribute(PATH, pathService.findAllPaths());
		return PATH;
	}
}