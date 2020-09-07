package com.QUILEIA.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	//Pages Redirections
	@GetMapping("/addOfficerPage")
	public String addOfficerPage() {
		return "addOfficer";
	}
	
	@GetMapping("/officerReportPage")
	public String officerReportPage() {
		return "officerReportSearch";
	}
	
	@GetMapping("/pathReportPage")
	public String pathReportPage() {
		return "pathReportSearch";
	}
	
	@GetMapping("/addPathPage")
	public String addPathPage() {
		return "addPath";
	}
}
