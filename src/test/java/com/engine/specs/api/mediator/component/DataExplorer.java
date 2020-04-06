package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.mediator.ScenarioMediator;

public class DataExplorer {
	private ScenarioMediator mediator;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T retrieveEntity(T entity, String resource) {
		entity = (T) with()
						.contentType("application/json")
						.get("/" + resource)
						.getBody()
						.as(entity.getClass());
		 
		 return entity;
	}

}
