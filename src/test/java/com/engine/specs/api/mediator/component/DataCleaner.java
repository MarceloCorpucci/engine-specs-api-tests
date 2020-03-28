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
	
	public int cleanUp(String id) {
		token = mediator.authenticate();
		
		return with()
				.header("Authorization", "Bearer " + token)
				.contentType("application/json")
				.delete("/engine/" + id)
				.getStatusCode();
	}
}
