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
import com.engine.specs.api.entity.builder.User;

import io.restassured.specification.RequestSpecification;

public class TestGetEngines {
	private String endPoint;
	private String email;
	private String password;
	private String token;
	private User user;
	
	private Engine engine;
	
	private RequestSpecification request;
	
	@Before
	public void setUp() throws IOException {
		String resourcePath = String.valueOf(System.getProperty("envProperties"));
		InputStream file = new FileInputStream(resourcePath);
		Properties testParams = new Properties();
		testParams.load(file);
		
		endPoint = testParams.getProperty("endPoint");
		email = testParams.getProperty("email");
		password = testParams.getProperty("password");
		
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
				
		token = given()
					.contentType("application/json")
					.body(user)
					.post(endPoint + "/users/login")
					.getBody()
					.jsonPath()
					.getString(
							String.format("%s", "access_token"));
		
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		given()
			.header("Authorization", "Bearer " + token)
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
//		request
//			.when()
//				.post(endPoint + "/engine")
//			.then()
//				.assertThat()
//				.statusCode(201)
//				.log()
//				.all();
		System.out.println(endPoint);
		System.out.println(email);
		System.out.println(password);
		System.out.println(token);
	}
}
