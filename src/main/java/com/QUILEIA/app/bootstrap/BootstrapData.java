package com.QUILEIA.app.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.QUILEIA.app.repos.OfficerRecordRepo;
import com.QUILEIA.app.repos.OfficerRepo;
import com.QUILEIA.app.repos.PathRepo;

@Component
public class BootstrapData implements CommandLineRunner {

	public BootstrapData(OfficerRepo officerRepo, PathRepo pathRepo, OfficerRecordRepo officersRecodsRepo) {
	}
	
	@Override
	public void run(String... args) {
		//Used for testing purposes and data initialization
	}
}
