package com.QUILEIA.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.services.ReportService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ServiceReportController {
	private final ReportService reportService;
	
	public ServiceReportController(ReportService pReportService) {
		super();
		reportService = pReportService;
	}
	
	//Search Reports
		@PostMapping(value = "/officerReport")
		public ArrayList<OfficerRecord> searchOfficerReport(@RequestBody String pId) {
			return reportService.searchOfficerReport(pId);
		}
		
		@PostMapping(value = "/pathReport")
		public ArrayList<OfficerRecord> searchPathReport(@RequestBody int pId) {
			return reportService.searchPathReport(pId);
		}
}
