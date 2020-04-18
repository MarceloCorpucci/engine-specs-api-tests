package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.mediator.ScenarioMediator;

public class DataExplorer<T> {
	private ScenarioMediator mediator;
	private T entity;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	public DataExplorer<T> basedOn(T entity) {
		this.entity = entity;
		return this;
	}

	public Object retrieve(String resource) {
		return with()
				.contentType("application/json")
				.get(resource)
				.getBody()
				.as(entity.getClass());
	}

}
