package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.ScenarioMediator;

public class DataInjector {
	//TODO: Change implementations to use generics.
	private ScenarioMediator mediator;
	private String token;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	
	public String inject(Engine engine) {
		//Add validation in case of not invoking useToken first.
		
		token = mediator.authenticate();
		
		return with()
				.header("Authorization", "Bearer " + token)
				.contentType("application/json")
				.body(engine)
				.post("/engine")
				.getBody()
				.jsonPath()
				.getString(
						String.format("%s", "engine._id.$oid"));
	}
}
