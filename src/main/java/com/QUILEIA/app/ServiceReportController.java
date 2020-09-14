package com.QUILEIA.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.services.ReportService;

@Controller
public class ServiceReportController {
	private final ReportService reportService;
	
	public ServiceReportController(ReportService pReportService) {
		super();
		reportService = pReportService;
	}
	
	//Search Reports
		@RequestMapping(value = "/officerReport/{id_officer}", method = RequestMethod.POST)
		public ArrayList<OfficerRecord> searchOfficerReport(@RequestParam(name="id_officer", required = true) String pId) {
			return reportService.searchOfficerReport(pId);
		}
		
		@RequestMapping(value = "/pathReport", method = RequestMethod.POST)
		public ArrayList<OfficerRecord> searchPathReport(@RequestParam(name = "id_path",required = true) int pId) {
			return reportService.searchPathReport(pId);
		}
}
