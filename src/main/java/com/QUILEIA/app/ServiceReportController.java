package com.QUILEIA.app;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.services.ReportService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ServiceReportController {
	private final ReportService reportService;
	
	public ServiceReportController(ReportService pReportService) {
		super();
		reportService = pReportService;
	}
	
	//Search Reports
		@PostMapping(value = "/officerReport/{pId}")
		public ArrayList<OfficerRecord> searchOfficerReport(@PathVariable String pId) {
			return reportService.searchOfficerReport(pId);
		}
		
		@PostMapping(value = "/pathReport/{pId}")
		public ArrayList<OfficerRecord> searchPathReport(@PathVariable int pId) {
			return reportService.searchPathReport(pId);
		}
}
