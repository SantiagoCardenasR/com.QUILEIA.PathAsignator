package com.QUILEIA.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.QUILEIA.app.model.Officer;
import com.QUILEIA.app.services.OfficerService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceOfficerControllerTest {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private OfficerService officersService;
	
	@Test
	@Sql("./officers_insert.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void getOfficersTest() {
		Iterable<Map<String, Object>> expectedOfficers = jdbcTemplate.queryForList("SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		String url = "http://localhost:8081/officers";
		Iterable<Officer> officers = (Iterable<Officer>) restTemplate.getForObject(url, Iterable.class);
		assertIterableEquals(expectedOfficers, officers);
	}
	
	@Test
	@Sql("./officers_delete.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void addOfficerTest() {
		String url = "http://localhost:8081/addOfficer/34567664511,Alex,Mason,8,ATS3568974522,0";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		Iterable<Officer> officers = restTemplate.postForObject(url, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedOfficers = jdbcTemplate.queryForList("SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		assertIterableEquals(expectedOfficers, officers);
	}
	
	@Test
	@Sql("./officers_insert.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void updateOfficerTest() {
		String url = "http://localhost:8081/editOfficer/0,Hank,Frank,12,ATS0212456,0";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		Officer officer = restTemplate.postForObject(url, entity, Officer.class);
		String expectedOfficerCode = jdbcTemplate.queryForObject("SELECT cod_officer FROM officer WHERE cod_officer = 0",String.class);
		String expectedOfficerName = jdbcTemplate.queryForObject("SELECT name FROM officer WHERE cod_officer = 0",String.class);
		String expectedOfficerLastName = jdbcTemplate.queryForObject("SELECT last_name FROM officer WHERE cod_officer = 0",String.class);
		int expectedOfficerYoex = jdbcTemplate.queryForObject("SELECT years_of_experience FROM officer WHERE cod_officer = 0",Integer.class);
		String expectedOfficerTsc = jdbcTemplate.queryForObject("SELECT transit_secretary_code FROM officer WHERE cod_officer = 0",String.class);
		int expectedOfficerActualPath = jdbcTemplate.queryForObject("SELECT actual_path_id FROM officer WHERE cod_officer = 0",Integer.class);
		int expectedOfficerAssignations = jdbcTemplate.queryForObject("SELECT assignations FROM officer WHERE cod_officer = 0",Integer.class);
		Officer expectedOfficer = new Officer(expectedOfficerCode,expectedOfficerName,expectedOfficerLastName,expectedOfficerYoex,expectedOfficerTsc,expectedOfficerActualPath,expectedOfficerAssignations);
		assertEquals(expectedOfficer, officer);
	}
	
	@Test
	@Sql("./officers_insert.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void deleteOfficerTest() {
		String url = "http://localhost:8081/deleteOfficer/0";
		Iterable<Officer> officers = restTemplate.getForObject(url, Iterable.class);
		Iterable<Map<String, Object>> expectedOfficers = jdbcTemplate.queryForList("SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		assertIterableEquals(expectedOfficers, officers);
	}
}
