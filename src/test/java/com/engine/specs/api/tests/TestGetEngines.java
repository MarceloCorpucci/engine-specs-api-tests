package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;

import io.restassured.specification.RequestSpecification;

public class TestGetEngines {
	private String endPoint;
	private Engine engine;
	private RequestSpecification request;
	
	@Before
	public void setUp() throws IOException {
//		Path resourcePath = Paths.get("src","test","resources", "env", "config");
		String resourcePath = String.valueOf(System.getProperty("envProperties"));
//		InputStream file = new FileInputStream(resourcePath.toAbsolutePath() + "/test.properties");
		InputStream file = new FileInputStream(resourcePath);
		Properties testProperties = new Properties();
		testProperties.load(file);
		
		endPoint = testProperties.getProperty("endPoint");
		
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		given()
			.contentType("application/json")
			.body(engine)
			.post(endPoint + "/engine");
		
//		engineId = given()
//				.contentType("application/json")
//				.body(engine)
//				.post(endPoint + "/engine")
//				.getBody()
//				.jsonPath()
//				.getString(
//						String.format("%s", "engine._id.$oid"));
		}
	
	@Test
	public void availableEnginesShouldHaveProperStatusCode() {
		request
			.when()
				.post(endPoint + "/engine")
			.then()
				.assertThat()
				.statusCode(201)
				.log()
				.all();
	}
}
