package com.QUILEIA.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.repos.OfficerRecordRepo;
import com.QUILEIA.app.repos.OfficerRepo;
import com.QUILEIA.app.repos.PathRepo;

@Controller
public class AppController 
{
	private final OfficerRepo officersRepo;
	private final PathRepo pathsRepo;
	private final OfficerRecordRepo officersRecordsRepo;
	
	public AppController(OfficerRepo officersRepo, PathRepo pathsRepo, OfficerRecordRepo officersRecordsRepo) 
	{
		super();
		this.officersRepo = officersRepo;
		this.pathsRepo = pathsRepo;
		this.officersRecordsRepo = officersRecordsRepo;
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
	
	@GetMapping("/officerReportPage")
	public String officerReportPage()
	{
		return "officerReportSearch";
	}
	
	@GetMapping("/pathReportPage")
	public String pathReportPage()
	{
		return "pathReportSearch";
	}
	
	@RequestMapping(value = "/addOfficer", method = RequestMethod.POST)
	public String addOfficer(@RequestParam(name="id_officer", required = true) String pId, @RequestParam(name= "name_officer", required = true) String pName, @RequestParam(name= "years_exp_officer", required = true) int pYoex, @RequestParam(name = "TSC_officer", required = true) String pTsc, @RequestParam(name = "id_path_officer", required = true) int pIdPath, Model model)
	{
		Optional<Path> actualPath = pathsRepo.findById(pIdPath);
		if(actualPath.get().getTraffic() >= 30)
		{
			Officer pOfficer = new Officer(pId,pName, pYoex, pTsc, pIdPath);
			String actualPathName = actualPath.get().getIsStreetOrKr() + actualPath.get().getNumber();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now(); 
			OfficerRecord newRecord = new OfficerRecord(pOfficer.getName(),actualPathName,dtf.format(now).toString(),pOfficer.getCode(),actualPath.get().getId());
			officersRecordsRepo.save(newRecord);
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
	
	@RequestMapping(value = "/editOfficer", method = RequestMethod.GET)
	public String editOfficerPage(@RequestParam(name="code", required = true) String pId, @RequestParam(name= "name", required = true) String pName, @RequestParam(name= "yoex", required = true) int pYoex, @RequestParam(name = "tsc", required = true) String pTsc, @RequestParam(name = "actualPathId", required = true) int pIdPath, Model model)
	{
		Officer officerToEdit = new Officer(pId, pName, pYoex, pTsc, pIdPath);
		model.addAttribute("officer",officerToEdit);
		return "editOfficer";
	}
	
	@RequestMapping(value = "/editOfficer", method = RequestMethod.POST)
	public String editOfficer(@RequestParam(name="id_officer", required = true) String pId, @RequestParam(name= "name_officer", required = true) String pName, @RequestParam(name= "years_exp_officer", required = true) int pYoex, @RequestParam(name = "TSC_officer", required = true) String pTsc, @RequestParam(name = "id_path_officer", required = true) int pIdPath, Model model)
	{
		Officer officerToUpdate = officersRepo.findById(pId).get();
		officerToUpdate.setName(pName);
		officerToUpdate.setYearsOfExperience(pYoex);
		officerToUpdate.setTransitSecretaryCode(pTsc);
		officerToUpdate.setActualPathId(pIdPath);
		
		Path actualPath = pathsRepo.findById(officerToUpdate.getActualPathId()).get();
		String actualPathName = actualPath.getIsStreetOrKr() + actualPath.getNumber();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		OfficerRecord newRecord = new OfficerRecord(officerToUpdate.getName(),actualPathName,dtf.format(now).toString(),officerToUpdate.getCode(),actualPath.getId());
		officersRecordsRepo.save(newRecord);
		officersRepo.save(officerToUpdate);
		model.addAttribute("officers",officersRepo.findAll());
		return "officers";
	}
	
	@RequestMapping(value = "/editPath", method = RequestMethod.GET)
	public String editPathPage(@RequestParam(name = "id_path",required = true) int pId, @RequestParam(name = "type_path",required = true) String pTipoVia, @RequestParam(name = "str_or_kr",required = true) String pStrOrKr, @RequestParam(name = "number_path",required = true) int pNumber, @RequestParam(name = "traffic_path",required = true) int pTraffic, Model model)
	{
		Path pathToEdit = pathsRepo.findById(pId).get();
		model.addAttribute("path",pathToEdit);
		return "editPath";
	}
	
	@RequestMapping(value = "/editPath", method = RequestMethod.POST)
	public String editPath(@RequestParam(name = "id_path",required = true) int pId, @RequestParam(name = "type_path",required = true) String pTipoVia, @RequestParam(name = "str_or_kr",required = true) String pStrOrKr, @RequestParam(name = "number_path",required = true) int pNumber, @RequestParam(name = "traffic_path",required = true) int pTraffic, Model model)
	{
		Path pathToUpdate = pathsRepo.findById(pId).get();
		pathToUpdate.setType(pTipoVia);
		pathToUpdate.setIsStreetOrKr(pStrOrKr);
		pathToUpdate.setNumber(pNumber);
		pathToUpdate.setTraffic(pTraffic);
		pathsRepo.save(pathToUpdate);
		model.addAttribute("paths",pathsRepo.findAll());
		return "paths";
	}
	
	@RequestMapping(value = "/deleteOfficer", method = RequestMethod.GET)
	public String deleteOfficer(@RequestParam(name="id_officer", required = true) String pId, Model model)
	{
		officersRepo.deleteById(pId);
		model.addAttribute("officers", officersRepo.findAll());
		return "officers";
	}
	
	@RequestMapping(value = "/deletePath", method = RequestMethod.GET)
	public String deltePath(@RequestParam(name = "id_path",required = true) int pId, Model model)
	{
		pathsRepo.deleteById(pId);
		model.addAttribute("paths", pathsRepo.findAll());
		return "paths";
	}
	
	@RequestMapping(value = "/officerReport", method = RequestMethod.POST)
	public String searchOfficerReport(@RequestParam(name="id_officer", required = true) String pId, Model model)
	{
		Iterable<OfficerRecord> officerRecords = officersRecordsRepo.findAll();
		ArrayList<OfficerRecord> officerRecordsToSend = new ArrayList<OfficerRecord>();
		officerRecords.forEach(new Consumer<OfficerRecord>() {
			@Override
			public void accept(OfficerRecord pOfficerRecord) 
			{
				if(pOfficerRecord.getOfficerId().equalsIgnoreCase(pId))
				{
					officerRecordsToSend.add(pOfficerRecord);
				}
			}
		});
		model.addAttribute("reports", officerRecordsToSend);
		return "officerReport";
	}
	
	@RequestMapping(value = "/pathReport", method = RequestMethod.POST)
	public String searchPathReport(@RequestParam(name = "id_path",required = true) int pId, Model model)
	{
		Iterable<OfficerRecord> pathsRecords = officersRecordsRepo.findAll();
		ArrayList<OfficerRecord> pathsRecordsToSend = new ArrayList<OfficerRecord>();
		pathsRecords.forEach(new Consumer<OfficerRecord>() {
			@Override
			public void accept(OfficerRecord pOfficerRecord) 
			{
				if(pOfficerRecord.getPathId() == pId)
				{
					pathsRecordsToSend.add(pOfficerRecord);
				}
			}
		});
		model.addAttribute("reports", pathsRecordsToSend);
		return "pathReport";
	}
}
