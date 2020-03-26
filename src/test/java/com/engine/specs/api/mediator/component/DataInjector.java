package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import com.engine.specs.api.entity.builder.Engine;

public class DataInjector {
	//TODO: Change implementations to use generics.
	private String token;
	
	public DataInjector usingToken(String token) {
		this.token = token;
		return this;
	}
	
	public String inject(Engine engine) {
		//Add validation in case of not invoking useToken first.
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
