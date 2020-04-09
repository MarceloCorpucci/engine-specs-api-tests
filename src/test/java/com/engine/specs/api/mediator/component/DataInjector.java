package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.given;

import com.engine.specs.api.mediator.ScenarioMediator;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class DataInjector<T> {
	private ScenarioMediator mediator;
	private Object objEntity;
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
											.setContentType("application/xml")
											.build();
		
		return spec
				.body(this.entity)
				.post("localhost:5000/api/" + resource)
				.getBody()
				.jsonPath()
				.getString(
					String.format("%s", resource + "._id.$oid"));
	}
	
	public DataInjector<T> cast(Class<T> clazz, Object obj) {
	    objEntity = clazz.isInstance(obj) ? clazz.cast(obj) : null;
	    return this;
	}
}
