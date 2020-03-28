package com.engine.specs.api.mediator.component;

import com.engine.specs.api.entity.builder.User;
import com.engine.specs.api.mediator.ScenarioMediator;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.with;

public class Authenticator {
	private User user;
	private ScenarioMediator mediator;
	
	public Authenticator() {
		this.user = new User();
	}
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	//We can hide this rest assured impl into another indirection.
	public String authenticate() {
		RestAssured.baseURI = mediator.testParams().getProperty("endPoint");
		user.setEmail(mediator.testParams().getProperty("email"));
		user.setPassword(mediator.testParams().getProperty("password"));
		
		return with()
				.contentType("application/json")
				.body(user)
				.post("/users/login")
				.getBody()
				.jsonPath()
				.getString(
						String.format("%s", "access_token"));
	}
}
