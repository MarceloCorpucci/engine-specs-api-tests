package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.mediator.ScenarioMediator;

public class DataCleaner {
	private ScenarioMediator mediator;
	private String token;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
		token = mediator.authenticate();
	}
	
	public int cleanUp(String property, String value, String resource) {
		
		String id = with()
						.contentType("application/json")
						.get("/" + resource + "/" + property + "/" + value)
						.getBody()
						.jsonPath()
						.getString(
							String.format("%s", resource + "._id.$oid"));
				
		return with()
				.header("Authorization", "Bearer " + token)
				.contentType("application/json")
				.delete("/" + resource + "/" + id)
				.getStatusCode();
	}
	
	public int cleanUp(String id, String resource) {
		token = mediator.authenticate();
		
		return with()
				.header("Authorization", "Bearer " + token)
				.contentType("application/json")
				.delete("/" + resource + "/" + id)
				.getStatusCode();
	}
}
