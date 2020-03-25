package com.engine.specs.api.mediator;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.entity.builder.User;

public class Authenticator {
	//TODO: Pending to add validations on each method and exception management.
	private User user;
	
	public Authenticator() {
		this.user = new User();
	}
	
	public Authenticator getToken() {
		return this;
	}
	
	public Authenticator basedOn(String email) {
		this.user.setEmail(email);
		return this;
	}
	
	public String and(String password) {
		this.user.setPassword(password);
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
