package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.mediator.ScenarioMediator;

public class DataCleaner {
	//TODO Change this to use generics
	private ScenarioMediator mediator;
	private String token;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	public DataCleaner usingToken(String token) {
		this.token = token;
		return this;
	}
	
	public void cleanUp(String id) {
		with()
		.header("Authorization", "Bearer " + token)
		.contentType("application/json")
		.delete("/engine/" + id);
	}
}
