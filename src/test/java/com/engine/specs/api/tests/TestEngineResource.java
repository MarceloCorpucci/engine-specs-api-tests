package com.engine.specs.api.tests;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

import com.engine.specs.api.entity.Engine;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEngineResource {
	private final String endPoint = "http://localhost:5000/api";
	private Engine engine;
	private RequestSpecification request;
	
	@Before
	public void setUp() {
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		request = given()
					.contentType("application/json")
					.body(engine)
					.log()
					.all();
	}
	
	@Test
	public void engineCreatedShouldHaveProperStatusCode() {
		request
			.when()
				.post(endPoint + "/engine")
			.then()
				.assertThat()
				.statusCode(201)
				.log()
				.all();
	}
	
	@Test
	public void engineCreatedShouldShowItsContent() {
		request
			.when()
				.post(endPoint + "/engine")
			.then()
				.assertThat()
				.body("engine.displacement", equalTo(engine.getdisplacement()))
				.and()
				.body("engine.forced_induction", equalTo(engine.isforced_induction()))
				.and()
				.body("engine.model", equalTo(engine.getmodel()))
				.and()
				.body("engine.power", equalTo(engine.getpower()))
				.and()
				.body("engine.valve_amount", equalTo(engine.getvalve_amount()));
	}
	
	@After
	public void tearDown() {
		String engineId = RestAssured.get(endPoint + "/engine/" + engine.getmodel())
										.getBody()
										.jsonPath()
										.getString(
												String.format("%s", "engine._id.$oid"));
		
		delete(endPoint + "/engine/" + engineId);
	}
}
