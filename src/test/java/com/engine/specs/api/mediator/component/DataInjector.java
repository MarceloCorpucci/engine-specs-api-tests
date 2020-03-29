package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.mediator.ScenarioMediator;

public class DataInjector {
	private ScenarioMediator mediator;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	public <T> String inject(T entity, String resource) {
		String token = mediator.authenticate();
		
		return with()
				.header("Authorization", "Bearer " + token)
				.contentType("application/json")
				.body(entity)
				.post("/" + resource)
				.getBody()
				.jsonPath()
				.getString(
					String.format("%s", resource + "._id.$oid"));
	}
}
