package com.QUILEIA.app;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.repos.OfficerRepo;
import com.QUILEIA.app.repos.PathRepo;

@Controller
public class AppController 
{
	private final OfficerRepo officersRepo;
	private final PathRepo pathsRepo;
	
	public AppController(OfficerRepo officersRepo, PathRepo pathsRepo) 
	{
		super();
		this.officersRepo = officersRepo;
		this.pathsRepo = pathsRepo;
	}


	@GetMapping("/officers")
	public String officersList(Model model)
	{
		model.addAttribute("officers", officersRepo.findAll());
		return "officers";
	}
	
	@GetMapping("/paths")
	public String pathsList(Model model)
	{
		model.addAttribute("paths", pathsRepo.findAll());
		return "paths";
	}
	
	@GetMapping("/addOfficerPage")
	public String addOfficerPage()
	{
		return "addOfficer";
	}
	
	@RequestMapping(value = "/addOfficer", method = RequestMethod.POST)
	public String addOfficer(@RequestParam(name="id_officer", required = true) String pId, @RequestParam(name= "name_officer", required = true) String pName, @RequestParam(name= "years_exp_officer", required = true) int pYoex, @RequestParam(name = "TSC_officer", required = true) String pTsc, @RequestParam(name = "id_path_officer", required = true) int pIdPath, Model model)
	{
		Optional<Path> actualPath = pathsRepo.findById(pIdPath);
		if(actualPath.get().getTraffic() >= 30)
		{
			Officer pOfficer = new Officer(pId,pName, pYoex, pTsc, pIdPath);
			officersRepo.save(pOfficer);
			model.addAttribute("officers", pathsRepo.findAll());
		}
		else
		{
			System.out.println("Path with low traffic, no Officer necessary");
		}
		
		return "officers";
	}
	
	@GetMapping("/addPathPage")
	public String addPathPage()
	{
		return "addPath";
	}
	
	@RequestMapping(value = "/addPath", method = RequestMethod.POST)
	public String addPath(@RequestParam(name = "id_path",required = true) int pId, @RequestParam(name = "type_path",required = true) String pTipoVia, @RequestParam(name = "str_or_kr",required = true) String pStrOrKr, @RequestParam(name = "number_path",required = true) int pNumber, @RequestParam(name = "traffic_path",required = true) int pTraffic, Model model)
	{
		if(pTraffic <= 100 && pTraffic > 0)
		{
			Path newPath = new Path(pId, pTipoVia, pStrOrKr, pNumber, pTraffic);
			pathsRepo.save(newPath);
			model.addAttribute("paths", pathsRepo.findAll());
		}
		else
		{
			System.out.println("The traffic is not valid");
		}
		return "paths";
	}
}
