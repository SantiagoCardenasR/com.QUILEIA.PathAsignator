package com.QUILEIA.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.services.OfficerService;

@Controller
public class ServiceOfficerController {
	public static final String BASE_URL = "/officers";
	public static final String OFFICERS = "officers";
	
	private final OfficerService officersService;

	public ServiceOfficerController(OfficerService officersService) {
		super();
		this.officersService = officersService;
	}
	
	@GetMapping(value = BASE_URL)
	public String getAllOfficers(Model model) {
		model.addAttribute(OFFICERS, officersService.findAllOfficers());
		return OFFICERS;
	}
	
	@RequestMapping(value = "/addOfficer", method = RequestMethod.POST)
	public String addOfficer(@RequestParam(name="id_officer", required = true) String pId, @RequestParam(name= "name_officer", required = true) String pName,  @RequestParam(name="lastName_officer", required = true) String pLastName, @RequestParam(name= "years_exp_officer", required = true) int pYoex, @RequestParam(name = "TSC_officer", required = true) String pTsc, @RequestParam(name = "id_path_officer", required = true) int pIdPath, Model model) {
		if(officersService.addOfficer(pId, pName, pLastName, pYoex, pTsc, pIdPath)) {
			model.addAttribute(OFFICERS, officersService.findAllOfficers());
		}
		return OFFICERS;
	}
	
	@RequestMapping(value = "/editOfficer", method = RequestMethod.GET)
	public String editOfficerPage(@RequestParam(name="code", required = true) String pId, @RequestParam(name= "name", required = true) String pName,@RequestParam(name= "lastName", required = true) String pLastName, @RequestParam(name= "yoex", required = true) int pYoex, @RequestParam(name = "tsc", required = true) String pTsc, @RequestParam(name = "actualPathId", required = true) int pIdPath,  @RequestParam(name = "numberOfChanges", required = true) int pChanges, Model model) {	
		Officer officerToEdit = new Officer(pId, pName,pLastName, pYoex, pTsc, pIdPath, pChanges);
		model.addAttribute("officer",officerToEdit);
		return "editOfficer";
	}
	
	@RequestMapping(value = "/editOfficer", method = RequestMethod.POST)
	public String editOfficer(@RequestParam(name="id_officer", required = true) String pId, @RequestParam(name= "name_officer", required = true) String pName, @RequestParam(name= "lastName_officer", required = true) String pLastName , @RequestParam(name= "years_exp_officer", required = true) int pYoex, @RequestParam(name = "TSC_officer", required = true) String pTsc, @RequestParam(name = "id_path_officer", required = true) int pIdPath, Model model) {	
		officersService.editOfficer(pId, pName, pLastName, pYoex, pTsc, pIdPath);
		model.addAttribute(OFFICERS,officersService.findAllOfficers());
		return OFFICERS;
	}
	
	@RequestMapping(value = "/deleteOfficer", method = RequestMethod.GET)
	public String deleteOfficer(@RequestParam(name="id_officer", required = true) String pId, Model model) {
		officersService.deleteOfficer(pId);
		model.addAttribute(OFFICERS, officersService.findAllOfficers());
		return OFFICERS;
	}
	
	@GetMapping(value = BASE_URL + "/{id}")
	public Officer getOfficerById(@PathVariable String id) {
		return officersService.findOfficerById(id);
	}
	
}
