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

import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.services.PathService;

import net.minidev.json.JSONObject;

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
		String url = "http://localhost:8081/addPath";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject pathObject = new JSONObject();
		pathObject.put("id", 0);
		pathObject.put("type", "Principal");
		pathObject.put("isStreetOrKr", "Calle");
		pathObject.put("number", 170);
		pathObject.put("traffic", 85);
		HttpEntity<String> entity = new HttpEntity<>(pathObject.toJSONString(), headers);
		ResponseEntity<Iterable> paths = restTemplate.exchange(url, HttpMethod.POST, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedPaths = jdbcTemplate.queryForList("SELECT cod_path AS id, type, is_street_or_kr AS isStreetOrKr, number, traffic FROM path");
		assertEquals(expectedPaths, paths.getBody());
	}
	
	@Test
	@Sql("./paths_insert.sql")
	@Sql(scripts = "./paths_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void editPathTest() {
		String url = "http://localhost:8081/editPath";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject pathObject = new JSONObject();
		pathObject.put("id", 0);
		pathObject.put("type", "Autopista");
		pathObject.put("isStreetOrKr", "Calle");
		pathObject.put("number", 122);
		pathObject.put("traffic", 90);
		HttpEntity<String> entity = new HttpEntity<>(pathObject.toJSONString(), headers);
		ResponseEntity<Iterable> paths = restTemplate.exchange(url, HttpMethod.PUT, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedPaths = jdbcTemplate.queryForList("SELECT cod_path AS id, type, is_street_or_kr AS isStreetOrKr, number, traffic FROM path");
		assertEquals(expectedPaths, paths.getBody());
	}
	
	@Test
	@Sql("./paths_insert.sql")
	@Sql(scripts = "./paths_delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void deletePathTest() {
		String url = "http://localhost:8081/deletePath/0";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("Body", headers);
		ResponseEntity<Iterable> paths = restTemplate.exchange(url, HttpMethod.DELETE, entity, Iterable.class);
		Iterable<Map<String, Object>> expectedPaths = jdbcTemplate.queryForList("SELECT cod_path AS id, type, is_street_or_kr AS isStreetOrKr, number, traffic FROM path");
		assertEquals(expectedPaths, paths.getBody());
	}

}
