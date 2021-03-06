package com.QUILEIA.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.QUILEIA.app.model.Officer;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceOfficerControllerTest {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Test
	@Sql("./officers_insert.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void getOfficersTest() {
		Iterable<Map<String, Object>> expectedOfficers = jdbcTemplate.queryForList(
				"SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		String url = "http://localhost:8081/officers";
		Iterable<Officer> officers = (Iterable<Officer>) restTemplate.getForObject(url, Iterable.class);
		assertIterableEquals(expectedOfficers, officers);
	}

	@Test
	@Sql("./officers_delete.sql")
	@Sql("./paths_insert.sql")
	@Sql(scripts = {"./officers_delete.sql", "./paths_delete.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void addOfficerTest() {
		String url = "http://localhost:8081/addOfficer";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject OfficerObject = new JSONObject();
		OfficerObject.put("code", "34567664511");
		OfficerObject.put("name", "Alex");
		OfficerObject.put("lastName", "Mason");
		OfficerObject.put("yearsOfExperience", 12);
		OfficerObject.put("transitSecretaryCode", "ATS091283764");
		OfficerObject.put("actualPathId", 0);
		HttpEntity<String> entity = new HttpEntity<>(OfficerObject.toJSONString(), headers);
		ResponseEntity<Iterable> officer = restTemplate.exchange(url, HttpMethod.POST, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedOfficers = jdbcTemplate.queryForList(
				"SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		assertEquals(expectedOfficers, officer.getBody());
	}
	
	@Test
	@Sql("./officers_insert.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void updateOfficerTest() {
		String url = "http://localhost:8081/editOfficer/";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject OfficerObject = new JSONObject();
		OfficerObject.put("code", "0");
		OfficerObject.put("name", "Francis");
		OfficerObject.put("lastName", "Jupiter");
		OfficerObject.put("yearsOfExperience", 12);
		OfficerObject.put("transitSecretaryCode", "ATS6565485645");
		OfficerObject.put("actualPathId", 0);
		HttpEntity<String> entity = new HttpEntity<>(OfficerObject.toJSONString(), headers);
		ResponseEntity<Iterable> officer = restTemplate.exchange(url, HttpMethod.PUT, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedOfficer = jdbcTemplate.queryForList("SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		assertEquals(expectedOfficer, officer.getBody());
	}
	
	@Test
	@Sql("./officers_insert.sql")
	@Sql(scripts = "./officers_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void deleteOfficerTest() {
		String url = "http://localhost:8081/deleteOfficer/0";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Iterable>  officers = restTemplate.exchange(url, HttpMethod.DELETE, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedOfficers = jdbcTemplate.queryForList(
				"SELECT cod_officer AS code, name, last_name AS lastName, years_of_experience AS yearsOfExperience, transit_secretary_code AS transitSecretaryCode, actual_path_id AS actualPathId, assignations FROM officer");
		assertEquals(expectedOfficers, officers.getBody());
	}
	 
	 
}
