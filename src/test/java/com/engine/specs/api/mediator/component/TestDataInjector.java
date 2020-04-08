package com.engine.specs.api.mediator.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.ScenarioMediator;

public class TestDataInjector {
	private DataInjector dataInjector;
	private ScenarioMediator mediator;
	private Authenticator authenticator;
	private ParamLoader paramLoader;
	private Engine engine;
	
	@Before
	public void setUp() {
		dataInjector = new DataInjector();
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
		engine = new Engine.Builder()
				.model("L61-1")
				.displacement(2200)
				.power(147)
				.forcedInduction(false)
				.build();

		String engineId = dataInjector.inject(engine, "engine");
		
		assertTrue(engineId != null);
	}
}
