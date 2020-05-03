package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.entity.factory.WarningPresetEntity;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataExplorer;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

import io.restassured.specification.RequestSpecification;

public class TestPostWarningPreset {
	private ScenarioMediator mediator;
	private DomainEntityFactory entityFactory;
	private RequestSpecification request;
	private String engineResource;
	private String engineId;
	private String warnEntityResource;
	private EngineEntity engine;
	private WarningPresetEntity warnPreset;
	
	@Before
	public void setUp() {
		this.initEntities();
		this.engineResource = mediator.commonParams().getProperty("engineResource");
		this.warnEntityResource = mediator.commonParams().getProperty("warnPresetResource");
		
		engine = entityFactory
					.createEntity("engine_min_repr")
					.getEngine();	
		engineId = mediator.inject(engine, engineResource);
		EngineEntity savedEngine = mediator.retrieveResource(new EngineEntity(), engineResource + "/" + engineId);
		
		warnPreset = entityFactory
						.createEntity("warning_preset_default")
						.getWarningPreset();
		warnPreset.setEngine(savedEngine);
		
		request = given()
					.contentType("application/json")
					.header("Authorization", "Bearer " + mediator.authenticate())
					.body(warnPreset)
					.log()
					.all();
	}
	
	@Test
	public void warnPresetCreatedShouldHaveProperStatusCode() {
		request
			.when()
				.post(mediator.testParams().getProperty("endPoint") + warnEntityResource)
			.then()
				.assertThat()
				.statusCode(201)
				.log()
				.all();
	}
	
	@After
	public void tearDown() {
		mediator.cleanUp("model", engine.getModel(), engineResource);
//		mediator.cleanUp("model", engine.getModel(), engineResource);
	}
	
	private void initEntities() {
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		System.setProperty("commonProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/common.properties");
		
		entityFactory = new DomainEntityFactory();
		
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		DataInjector<EngineEntity> dataInjector = new DataInjector<EngineEntity>();
		DataExplorer<EngineEntity> engineExplorer = new DataExplorer<EngineEntity>();
		DataCleaner dataCleaner = new DataCleaner();
		
		mediator = new ScenarioMediator();
		mediator.setParamLoader(paramLoader);
		mediator.setAuthenticator(authenticator);
		mediator.setEngineInjector(dataInjector);
		mediator.setEngineExplorer(engineExplorer);
		mediator.setDataCleaner(dataCleaner);
	}
}
