package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.mediator.ScenarioMediator;

public class DataInjector<T> {
	private ScenarioMediator mediator;
	private T entity;
	
	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}
	
	public DataInjector<T> inject(T entity) {
		this.entity = entity;
		return this;
	}
	
	public String in(String resource) {
		String token = mediator.authenticate();
		
		return with()
				.contentType("application/json")
				.header("Authorization", "Bearer " + token)
				.body(entity)
				.post(resource)
				.getBody()
				.jsonPath()
				.getString(
					String.format("%s", getEntityNameFrom(resource) + "._id.$oid"));
	}
	
	private String getEntityNameFrom(String resourcePath) {
		String[] name = resourcePath.split("/");
		int i = name.length;
		return name[i - 1];
	}

}
