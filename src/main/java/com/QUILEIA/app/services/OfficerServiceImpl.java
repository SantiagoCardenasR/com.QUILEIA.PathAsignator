package com.QUILEIA.app.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.repos.OfficerRecordRepo;
import com.QUILEIA.app.repos.OfficerRepo;
import com.QUILEIA.app.repos.PathRepo;

@Service
public class OfficerServiceImpl implements OfficerService {
	private final OfficerRepo officersRepo;
	private final PathRepo pathsRepo;
	private final OfficerRecordRepo officersRecordsRepo;
	
	public OfficerServiceImpl(OfficerRepo officersRepo, PathRepo pPathsRepo, OfficerRecordRepo pOfficersRecordsRepo) {
		super();
		this.officersRepo = officersRepo;
		this.pathsRepo = pPathsRepo;
		this.officersRecordsRepo = pOfficersRecordsRepo;
	}

	@Override
	public Officer findOfficerById(String pId) {
		return officersRepo.findById(pId).get();
	}

	@Override
	public Iterable<Officer> findAllOfficers() {
		return officersRepo.findAll();
	}

	@Override
	public boolean addOfficer(String pId, String pName, String pLastName, int pYoex, String pTsc, int pIdPath) {
		
		Optional<Path> actualPath = pathsRepo.findById(pIdPath);
		if(actualPath.get().getTraffic() >= 30) {
			Officer pOfficer = new Officer(pId,pName,pLastName, pYoex, pTsc, pIdPath, 1);
			String actualPathName = actualPath.get().getIsStreetOrKr() + actualPath.get().getNumber();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now(); 
			OfficerRecord newRecord = new OfficerRecord(pOfficer.getName(),actualPathName,dtf.format(now),pOfficer.getCode(),actualPath.get().getId());
			officersRecordsRepo.save(newRecord);
			officersRepo.save(pOfficer);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editOfficer(String pId, String pName, String pLastName, int pYoex, String pTsc, int pIdPath) {
		boolean response = false;
		Iterable<OfficerRecord> officerRecords = officersRecordsRepo.findAll();
		officerRecords.forEach(new Consumer<OfficerRecord> () {
			@Override
			public void accept(OfficerRecord pRecord) {
				if(pRecord.getOfficerId().equalsIgnoreCase(pId) && pRecord.getPathId() == pIdPath && officersRepo.findById(pId).isPresent() ) {
						Officer actualOfficer = officersRepo.findById(pId).get();
						int numberOfChanges = actualOfficer.getAssignations();
						if(numberOfChanges < 3) {
							numberOfChanges++;
							actualOfficer.setAssignations(numberOfChanges);
							officersRepo.save(actualOfficer);
						}
				}
			}
		});
		
		Officer actualOfficer = officersRepo.findById(pId).get();
		if(actualOfficer != null)
		{
			int changes = actualOfficer.getAssignations();
			
			if(changes < 3) {
				Officer officerToUpdate = officersRepo.findById(pId).get();
				officerToUpdate.setName(pName);
				officerToUpdate.setLastName(pLastName);
				officerToUpdate.setYearsOfExperience(pYoex);
				officerToUpdate.setTransitSecretaryCode(pTsc);
				officerToUpdate.setActualPathId(pIdPath);
				
				Path actualPath = pathsRepo.findById(officerToUpdate.getActualPathId()).get();
				String actualPathName = actualPath.getIsStreetOrKr() + actualPath.getNumber();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now(); 
				OfficerRecord newRecord = new OfficerRecord(officerToUpdate.getName(),actualPathName,dtf.format(now),officerToUpdate.getCode(),actualPath.getId());
				officersRecordsRepo.save(newRecord);
				officersRepo.save(officerToUpdate);
				response = true;
			} 
		}
		return response;
	}

	@Override
	public void deleteOfficer(String pId) {
		officersRepo.deleteById(pId);
	}
}
