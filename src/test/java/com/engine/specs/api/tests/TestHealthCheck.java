package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.ParamLoader;

public class TestHealthCheck {
	@Test
	public void testHealthCheck() {
		ParamLoader paramLoader = new ParamLoader();
		ScenarioMediator mediator = new ScenarioMediator();
		mediator.setParamLoader(paramLoader);
		
		given()
			.contentType("application/json")
		.when()
			.get(mediator.testParams().getProperty("endPoint") + "/health_check")
		.then()
			.assertThat()
			.statusCode(200);
	}
}
