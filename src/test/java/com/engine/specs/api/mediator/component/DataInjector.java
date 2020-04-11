package com.engine.specs.api.mediator.component;

import com.engine.specs.api.mediator.ScenarioMediator;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

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
		
		RequestSpecification spec = new RequestSpecBuilder()
											.addHeader("Authorization", "Bearer " + token)
											.setContentType("application/json")
											.setBaseUri("http://localhost:5000/api")
											.setBody(entity)
											.build();
		
		return RestAssured.given(spec)
				.post("/engines/" + resource)
				.getBody()
				.jsonPath()
				.getString(
					String.format("%s", resource + "._id.$oid"));
	}

}
