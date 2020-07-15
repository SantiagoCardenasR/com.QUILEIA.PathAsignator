package com.QUILEIA.app.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.repos.OfficerRepo;
import com.QUILEIA.app.repos.PathRepo;

@Component
public class BootstrapData implements CommandLineRunner
{
	private final OfficerRepo officerRepo;
	private final PathRepo pathRepo;
	
	public BootstrapData(OfficerRepo officerRepo, PathRepo pathRepo) 
	{
		super();
		this.officerRepo = officerRepo;
		this.pathRepo = pathRepo;
	}

	@Override
	public void run(String... args) throws Exception 
	{
		/*
		 * Officer rookie1 = new Officer("02156894562","Danel Hernandez", 1,
		 * "AT32659844",0); Path calle100 = new
		 * Path(0,"Carretera principal","Calle",100,40); officerRepo.save(rookie1);
		 * pathRepo.save(calle100);
		 * 
		 * Officer captain = new Officer("65894512","Jhon Morales", 15,
		 * "AT659845111",1); Path AvenidaSuba = new
		 * Path(1,"Carretera principal","Carrera",145,80); officerRepo.save(captain);
		 * pathRepo.save(AvenidaSuba);
		 * 
		 * Path calle170 = new Path(2,"Carretera secundaria","Calle",170,50);
		 * pathRepo.save(calle170);
		 */
		
		System.out.println("Started in Bootstrap");
		System.out.println("Number of officers in the force: " + officerRepo.count());
		System.out.println("Number of paths assigned: " + pathRepo.count());
	}

}
