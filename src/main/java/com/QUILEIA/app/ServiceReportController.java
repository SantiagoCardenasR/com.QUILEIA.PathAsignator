package com.QUILEIA.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.QUILEIA.app.services.ReportService;

@Controller
public class ServiceReportController {
	private final ReportService reportService;
	
	public ServiceReportController(ReportService pReportService) {
		super();
		reportService = pReportService;
	}
	
	//Search Reports
		@RequestMapping(value = "/officerReport", method = RequestMethod.POST)
		public String searchOfficerReport(@RequestParam(name="id_officer", required = true) String pId, Model model) {
			model.addAttribute("reports", reportService.searchOfficerReport(pId));
			return "officerReport";
		}
		
		@RequestMapping(value = "/pathReport", method = RequestMethod.POST)
		public String searchPathReport(@RequestParam(name = "id_path",required = true) int pId, Model model) {
			model.addAttribute("reports", reportService.searchPathReport(pId));
			return "pathReport";
		}
}
