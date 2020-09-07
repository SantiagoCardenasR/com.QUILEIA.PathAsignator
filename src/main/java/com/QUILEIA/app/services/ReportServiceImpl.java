package com.QUILEIA.app.services;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.springframework.stereotype.Service;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.repos.OfficerRecordRepo;

@Service
public class ReportServiceImpl implements ReportService {
	
	private final OfficerRecordRepo officersRecordsRepo;
	
	public ReportServiceImpl(OfficerRecordRepo officersRecordsRepo) {
		super();
		this.officersRecordsRepo = officersRecordsRepo;
	}

	@Override
	public ArrayList<OfficerRecord> searchOfficerReport(String pId) {
		Iterable<OfficerRecord> officerRecords = officersRecordsRepo.findAll();
		ArrayList<OfficerRecord> officerRecordsToSend = new ArrayList<OfficerRecord>();
		officerRecords.forEach(new Consumer<OfficerRecord>() {
			@Override
			public void accept(OfficerRecord pOfficerRecord) {
				if(pOfficerRecord.getOfficerId().equalsIgnoreCase(pId)) {
					officerRecordsToSend.add(pOfficerRecord);
				}
			}
		});
		return officerRecordsToSend;
	}

	@Override
	public ArrayList<OfficerRecord> searchPathReport(int pId) {
		Iterable<OfficerRecord> pathsRecords = officersRecordsRepo.findAll();
		ArrayList<OfficerRecord> pathsRecordsToSend = new ArrayList<OfficerRecord>();
		pathsRecords.forEach(new Consumer<OfficerRecord>() {
			@Override
			public void accept(OfficerRecord pOfficerRecord) {
				if(pOfficerRecord.getPathId() == pId) {
					pathsRecordsToSend.add(pOfficerRecord);
				}
			}
		});
		return pathsRecordsToSend;
	}

}
