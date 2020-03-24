package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;

import io.restassured.RestAssured;

public class TestDeleteEngineResource {
	private final String endPoint = "http://localhost:5000/api";
	private Engine engine;
	private String engineId;
	
	@Before
	public void setUp() {
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		engineId = given()
					.contentType("application/json")
					.body(engine)
					.post(endPoint + "/engine")
					.getBody()
					.jsonPath()
					.getString(
							String.format("%s", "engine._id.$oid"));
	}
	
	@Test
	public void engineDeletedShouldHaveProperStatusCode() {
			RestAssured
				.when()
					.delete(endPoint + "/engine/" + engineId)
				.then()
					.assertThat()
					.statusCode(204);
	}
}
