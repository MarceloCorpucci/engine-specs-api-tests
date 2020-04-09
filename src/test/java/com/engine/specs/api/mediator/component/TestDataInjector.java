package com.engine.specs.api.mediator.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.mediator.ScenarioMediator;

public class TestDataInjector {
	private DataInjector<EngineEntity> dataInjector;
	private ScenarioMediator mediator;
	private Authenticator authenticator;
	private ParamLoader paramLoader;
//	private Engine engine;
	private EngineEntity engine;
	
	@Before
	public void setUp() {
		dataInjector = new DataInjector<EngineEntity>();
		mediator = new ScenarioMediator();
		
		paramLoader = new ParamLoader();
		authenticator = new Authenticator();
		mediator.setAuthenticator(authenticator);
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		mediator.setParamLoader(paramLoader);
		
		dataInjector.setMediator(mediator);
	}
	
	@Test
	public void dataInjectorShouldManageGenerics() {
//		engine = new Engine.Builder()
//				.model("L61-1")
//				.displacement(2200)
//				.power(147)
//				.forcedInduction(false)
//				.build();
		
		engine = new EngineEntity();
		engine.setModel("L61-1");
		engine.setDisplacement(2200);
		engine.setPower(147);
		engine.setForcedInduction(false);
		
		String engineId = dataInjector.inject(engine).in("engine");
		
		assertTrue(engineId != null);
	}
}
