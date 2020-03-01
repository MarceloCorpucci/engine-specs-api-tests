package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.factory.Engine;
import com.engine.specs.api.entity.factory.EngineFactory;

import io.restassured.specification.RequestSpecification;

public class TestGetEngines {
	private final String endPoint = "http://localhost:5000/api";
	private RequestSpecification request;
	
	@Before
	public void setUp() {
		EngineFactory factory = new EngineFactory();
		factory.getJson();
		
//		given()
//			.contentType("application/json")
//			.body("")
//			.post(endPoint + "/engine");
		}
	
	@Test
	public void availableEnginesShouldHaveProperStatusCode() {
//		request
//			.when()
//				.post(endPoint + "/engine")
//			.then()
//				.assertThat()
//				.statusCode(201)
//				.log()
//				.all();
	}
}
