package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class TestGetEngines {
	private ScenarioMediator mediator;
	
	private Engine engine;
	private String resource;
	private String engineId;
	
	@Before
	public void setUp() throws IOException {	
		this.initEntities();
		
		engine = new Engine.Builder()
								.model("L61")
								.displacement(2200)
								.power(147)
								.forcedInduction(false)
								.build();
		
		resource = mediator.commonParams().getProperty("engineResource");
		engineId = mediator.inject(engine, resource);
	}
	
	@Test
	public void availableEnginesShouldHaveProperStatusCode() {
		given()
			.contentType("application/json")
		.when()
			.get(mediator.testParams().getProperty("endPoint") + resource + "/" + engineId)
		.then()
			.assertThat()
			.statusCode(200)
			.log()
			.all();
	}
	
	@After
	public void tearDown() {
		mediator.cleanUp(engineId, resource);
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
