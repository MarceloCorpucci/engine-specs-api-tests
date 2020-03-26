package com.engine.specs.api.mediator.component;

import java.io.IOException;

import com.engine.specs.api.entity.builder.User;
import com.engine.specs.api.mediator.ScenarioMediator;

public class Authenticator {
	//TODO: Pending to add validations on each method and exception management.
	private User user;
	private ScenarioMediator mediator;
	
	public Authenticator() {
		this.user = new User();
		this.mediator = new ScenarioMediator();
	}
	
	public Authenticator getToken() {
		return this;
	}
	
	public Authenticator basedOn(String email) {
		this.user.setEmail(email);
		return this;
	}
	
	public String and(String password) throws IOException {
		this.user.setPassword(password);
		return this.authenticate(user);
	}
	
	//We can hide this rest assured impl into another indirection.
	private String authenticate(User user) throws IOException {
		return mediator.request()
				.contentType("application/json")
				.body(user)
				.post("/users/login")
				.getBody()
				.jsonPath()
				.getString(
						String.format("%s", "access_token"));
	}
}
