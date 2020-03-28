package com.engine.specs.api.mediator.component;

import com.engine.specs.api.entity.builder.User;
import com.engine.specs.api.mediator.ScenarioMediator;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.with;

public class Authenticator {
	//TODO: Pending to add validations on each method and exception management.
	private User user;
	private ScenarioMediator mediator;
	
	public Authenticator() {
		this.user = new User();
	}
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	public Authenticator getToken() {
		return this;
	}
	
	public Authenticator basedOn(String email) {
		this.user.setEmail(email);
		return this;
	}
	
	public Authenticator and(String password) {
		this.user.setPassword(password);
		return this;
	}
	
	public String against(String endPoint) {
		RestAssured.baseURI = endPoint;
		return this.authenticate(user);
	}
	
	//We can hide this rest assured impl into another indirection.
	private String authenticate(User user) {
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
