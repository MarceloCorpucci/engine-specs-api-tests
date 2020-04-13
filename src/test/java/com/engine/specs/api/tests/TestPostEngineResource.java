package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.ParamLoader;

import io.restassured.specification.RequestSpecification;

public class TestPostEngineResource {
	private ScenarioMediator mediator;
	private RequestSpecification request;
	private String resource;
	private Engine engine;
	
	@Before
	public void setUp() {
		this.initEntities();
		this.resource = "/engines/engine";
		
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		request = given()
					.contentType("application/json")
					.header("Authorization", "Bearer " + mediator.authenticate())
					.body(engine)
					.log()
					.all();
	}
	
	@Test
	public void engineCreatedShouldHaveProperStatusCode() {
		request
			.when()
				.post(mediator.testParams().getProperty("endPoint") + resource)
			.then()
				.assertThat()
				.statusCode(201)
				.log()
				.all();
	}
	
	@Test
	public void engineCreatedShouldShowItsContent() {
		request
			.when()
				.post(mediator.testParams().getProperty("endPoint") + resource)
			.then()
				.assertThat()
				.body("engine.displacement", equalTo(engine.getDisplacement()))
				.and()
				.body("engine.forced_induction", equalTo(engine.isForcedInduction()))
				.and()
				.body("engine.model", equalTo(engine.getModel()))
				.and()
				.body("engine.power", equalTo(engine.getPower()))
				.and()
				.body("engine.valve_amount", equalTo(engine.getValveAmount()));
	}
	
	@After
	public void tearDown() {
		mediator.cleanUp("model", engine.getModel(), resource);
	}
	
	private void initEntities() {
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		DataCleaner dataCleaner = new DataCleaner();
		
		mediator = new ScenarioMediator();
		mediator.setParamLoader(paramLoader);
		mediator.setAuthenticator(authenticator);
		mediator.setDataCleaner(dataCleaner);
	}
}
