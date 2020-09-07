package com.QUILEIA.app.services;

import java.util.ArrayList;

import com.QUILEIA.app.model.OfficerRecord;

public interface ReportService {
	
	public ArrayList<OfficerRecord> searchOfficerReport(String pId);
	
	public ArrayList<OfficerRecord> searchPathReport(int pId);
}
