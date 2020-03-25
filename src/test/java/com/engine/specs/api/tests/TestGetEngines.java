package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.entity.builder.User;

import io.restassured.RestAssured;

public class TestGetEngines {
	private String email;
	private String password;
	private String token;
	private User user;
	
	private Engine engine;
	private String engineId;
	
	@Before
	public void setUp() throws IOException {
		String resourcePath = String.valueOf(System.getProperty("envProperties"));
		InputStream file = new FileInputStream(resourcePath);
		Properties testParams = new Properties();
		testParams.load(file);
		
		email = testParams.getProperty("email");
		password = testParams.getProperty("password");
		
	    RestAssured.baseURI = testParams.getProperty("endPoint");;
	    RestAssured.port = 443;
		
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
				
		token = given()
					.contentType("application/json")
					.body(user)
					.post("/users/login")
					.getBody()
					.jsonPath()
					.getString(
							String.format("%s", "access_token"));
		
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
					.post("/engine")
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
			.get("/engine/" + engineId)
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
		.delete("/engine/" + engineId);
	}
}
