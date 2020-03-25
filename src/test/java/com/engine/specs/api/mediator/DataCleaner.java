package com.engine.specs.api.mediator;

import static io.restassured.RestAssured.with;

public class DataCleaner {
	//TODO Change this to use generics
	private String token;
	
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
