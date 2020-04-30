package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Type;

import com.engine.specs.api.mediator.ScenarioMediator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.specification.RequestSpecification;

public class DataExplorer<T> {
	private ScenarioMediator mediator;
	private T entity;
	private RequestSpecification spec;

	public void setMediator(ScenarioMediator mediator) {
		this.mediator = mediator;
	}

	public DataExplorer<T> basedOn(T entity) {
		this.entity = entity;
		// TODO: This config should be managed by an object. Like a bridge or proxy maybe
		spec = new RequestSpecBuilder().setBaseUri(mediator.testParams().getProperty("endPoint"))
				.setContentType("application/json").setConfig(RestAssured.config().objectMapperConfig(
						new ObjectMapperConfig().jackson2ObjectMapperFactory(new Jackson2ObjectMapperFactory() {
							@Override
							public ObjectMapper create(Type cls, String charset) {
								ObjectMapper objectMapper = new ObjectMapper();
								objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
								return objectMapper;
							}
						})))
				.build();

		return this;
	}

	public Object retrieve(String resource) {
		Object obj = given()
						.spec(spec)
						.get(resource)
						.getBody()
						.as(entity.getClass());
		return obj;
	}

}
