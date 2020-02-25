package com.engine.specs.api.tests;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

import com.engine.specs.api.entity.Engine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEngineResource {
	private final String endPoint = "http://localhost:5000/api";
	private Engine engine;
	
	@Before
	public void setUp() {
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
	}
	
	@Test
	public void engineCreatedSuccessfuly() {
		given()
			.contentType("application/json")
			.body(engine)
			.log()
			.all()
		.when()
			.post(endPoint + "/engine")
		.then()
			.assertThat()
			.statusCode(201)
			.log()
			.all();
	}
	
	@After
	public void tearDown() {
		String engineId = "";
		//delete(endPoint + "/engine/" + engineId);
	}
}
