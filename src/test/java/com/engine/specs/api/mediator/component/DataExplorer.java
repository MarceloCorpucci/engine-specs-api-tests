package com.engine.specs.api.mediator.component;

import static io.restassured.RestAssured.with;

import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class DataExplorer { //<T> {
	//TODO: Maybe return a JSON object and cast to object in the test is easier.
//	private ScenarioMediator mediator;
//	private T entity;
	
//	public void setMediator(ScenarioMediator mediator) {
//		this.mediator = mediator;
//	}
//	
//	public DataExplorer<T> basedOn(T entity) {
//		this.entity = entity;
//		return this;
//	}
	
	public DataExplorer() {
		RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
				new Jackson2ObjectMapperFactory() {
					@Override
					public ObjectMapper create(Type cls, String charset) {
						ObjectMapper objectMapper = new ObjectMapper();
				        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
				        return objectMapper;
					}
				}
		));
	}

	public <T> Object retrieve(T entity, String resource) {
		return with()
				.contentType("text/html; charset=utf-8")
				.get(resource)
				.getBody()
				.as(entity.getClass());
	}

}
