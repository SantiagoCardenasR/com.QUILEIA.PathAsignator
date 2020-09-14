package com.QUILEIA.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.services.PathService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServicePathControllerTest {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PathService pathService;
	
	@Test
	@Sql("./paths_insert.sql")
	@Sql(scripts = "./paths_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void getPathsTest() {
		Iterable<Map<String, Object>> expectedPaths = jdbcTemplate.queryForList("SELECT cod_path AS id, type, is_street_or_kr AS isStreetOrKr, number, traffic FROM path");
		String url = "http://localhost:8081/paths";
		Iterable<Path> paths = (Iterable<Path>) restTemplate.getForObject(url, Iterable.class);
		assertIterableEquals(expectedPaths, paths);
	}
	
	@Test
	@Sql("./paths_delete.sql")
	@Sql(scripts = "./paths_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void addPathTest() {
		String url = "http://localhost:8081/addPath/3,Principal,Carrera,155,60";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		Iterable<Path> paths = restTemplate.postForObject(url, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedPaths = jdbcTemplate.queryForList("SELECT cod_path AS id, type, is_street_or_kr AS isStreetOrKr, number, traffic FROM path");
		assertIterableEquals(expectedPaths, paths);
	}

}
