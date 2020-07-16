package com.QUILEIA.app.bootstrap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.model.OfficerRecord;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.repos.OfficerRecordRepo;
import com.QUILEIA.app.repos.OfficerRepo;
import com.QUILEIA.app.repos.PathRepo;

@Component
public class BootstrapData implements CommandLineRunner
{
	private final OfficerRepo officerRepo;
	private final PathRepo pathRepo;
	private final OfficerRecordRepo officersRecodsRepo; 
		
	

	public BootstrapData(OfficerRepo officerRepo, PathRepo pathRepo, OfficerRecordRepo officersRecodsRepo) 
	{
		super();
		this.officerRepo = officerRepo;
		this.pathRepo = pathRepo;
		this.officersRecodsRepo = officersRecodsRepo;
	}



	@Override
	public void run(String... args) throws Exception 
	{
		
//		  if(officerRepo.count() > 0) 
//		  { 
//			  Iterable<Officer> officers = officerRepo.findAll(); 
//			  officers.forEach(new Consumer<Officer>() {
//		  @Override 
//		  public void accept(Officer pOfficer) 
//		  { 
//			  Path pathOfOfficer = pathRepo.findById(pOfficer.getActualPathId()).get(); 
//			  String pathName =pathOfOfficer.getIsStreetOrKr() + pathOfOfficer.getNumber();
//			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			  LocalDateTime now = LocalDateTime.now(); 
//			  OfficerRecord newRecord = new OfficerRecord(pOfficer.getName(), pathName,dtf.format(now).toString(), pOfficer.getCode(), pathOfOfficer.getId());
//			  officersRecodsRepo.save(newRecord); 
//		  } 
//		  }); 
//		  }
		 
		System.out.println("Started in Bootstrap");
		System.out.println("Number of officers in the force: " + officerRepo.count());
		System.out.println("Number of paths assigned: " + pathRepo.count());
		System.out.println("Number of Officers records: " + officersRecodsRepo.count());
	}

}
