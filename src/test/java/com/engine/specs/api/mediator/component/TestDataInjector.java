package com.engine.specs.api.mediator.component;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.mediator.ScenarioMediator;

public class TestDataInjector {
	private DataInjector<Engine> dataInjector;
	private ScenarioMediator mediator;
	private Authenticator authenticator;
	private ParamLoader paramLoader;
	private Engine engine;
	private DataCleaner dataCleaner;
//	private EngineEntity engine;
	
	@Before
	public void setUp() {
		dataInjector = new DataInjector<Engine>();
		mediator = new ScenarioMediator();
		dataCleaner = new DataCleaner();
		
		paramLoader = new ParamLoader();
		authenticator = new Authenticator();
		mediator.setAuthenticator(authenticator);
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		mediator.setParamLoader(paramLoader);
		mediator.setDataCleaner(dataCleaner);
		
		dataInjector.setMediator(mediator);
	}
	
	@Test
	public void dataInjectorShouldManageGenerics() {
		engine = new Engine.Builder()
				.model("L61-2")
				.displacement(2200)
				.power(147)
				.forcedInduction(false)
				.build();
		
//		engine = new EngineEntity();
//		engine.setModel("L61-1");
//		engine.setDisplacement(2200);
//		engine.setPower(147);
//		engine.setForcedInduction(false);
		
		String engineId = dataInjector.inject(engine).in("/engines/engine");
		
		assertTrue(engineId != null);
	}
	
	@After
	public void tearDown() {
		mediator.cleanUp("model", engine.getModel(), "/engines/engine");	
	}
}
