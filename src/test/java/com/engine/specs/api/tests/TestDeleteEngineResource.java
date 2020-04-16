package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class TestDeleteEngineResource {
	private ScenarioMediator mediator;
	
	private Engine engine;
	private String engineId;
	private String resource;
	
	@Before
	public void setUp() {
		this.initEntities();
		
		resource = mediator.commonParams().getProperty("engineResource");
		
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		engineId = mediator.inject(engine, resource);
	}
	
	@Test
	public void engineDeletedShouldHaveProperStatusCode() {
		given()
			.contentType("application/json")
			.header("Authorization", "Bearer " + mediator.authenticate())
		.when()
			.delete(mediator.testParams().getProperty("endPoint") + resource + '/' + engineId)
		.then()
			.assertThat()
			.statusCode(204);
	}
	
	private void initEntities() {
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		DataInjector<Engine> dataInjector = new DataInjector<Engine>();
		DataCleaner dataCleaner = new DataCleaner();
		
		mediator = new ScenarioMediator();
		mediator.setParamLoader(paramLoader);
		mediator.setAuthenticator(authenticator);
		mediator.setDataInjector(dataInjector);
		mediator.setDataCleaner(dataCleaner);
	}
}
