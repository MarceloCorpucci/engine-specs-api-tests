package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.ScenarioMediator;

public class TestGetEngines {
	private ScenarioMediator mediator;
	private String token;
	
	private Engine engine;
	private String engineId;
	
	@Before
	public void setUp() throws IOException {
		mediator = new ScenarioMediator();
		token = mediator.authenticate();
		
		engine = new Engine.Builder()
								.model("L61-1")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		engineId = given()
					.header("Authorization", "Bearer " + token)
					.contentType("application/json")
					.body(engine)
					.post("http://localhost:5000/api/engine")
					.getBody()
					.jsonPath()
					.getString(
							String.format("%s", "engine._id.$oid"));
				
		}
	
	@Test
	public void availableEnginesShouldHaveProperStatusCode() {
		given()
			.contentType("application/json")
		.when()
			.get("http://localhost:5000/api/engine/" + engineId)
		.then()
			.assertThat()
			.statusCode(200)
			.log()
			.all();

	}
	
	@After
	public void tearDown() {
		given()
		.header("Authorization", "Bearer " + token)
		.contentType("application/json")
		.delete("http://localhost:5000/api/engine/" + engineId);
	}
}
