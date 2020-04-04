package com.engine.specs.api.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.factory.Engine;
import com.engine.specs.api.entity.factory.EngineLoader;
import com.engine.specs.api.entity.factory.EntityLoader;
import com.engine.specs.api.mediator.ScenarioMediator;

public class TestPostInjectionMap {
	private ScenarioMediator mediator;
	
	@Before
	public void setUp() {
		this.initEntities();
		//Need an engine
		//an ecu
		//an user
	}
	
	@Test
	public void injectionMapCreatedShouldHaveProperStatusCode() {
		EntityLoader<Engine> loader = new EngineLoader();
		Engine engine = loader.get("full_repr").fromJsonResource();
		System.out.println(engine);
	}
	
	@After
	public void tearDown() {
		
	}
	
	private void initEntities() {
		mediator = new ScenarioMediator();
	}
}
