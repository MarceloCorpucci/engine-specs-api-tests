package com.engine.specs.api.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class TestPostInjectionMap {
	private DomainEntityFactory entityFactory;
	private EngineEntity engine;
	private ScenarioMediator mediator;
	private String engineId;
	
	@Before
	public void setUp() {
		this.initEntities();
		
		engine = entityFactory
					.createEntity("engine_min_repr")
					.getEngine();
		
		engineId = mediator.inject(engine, "/engines/engine");
		
	}
	
	@Test
	public void injectionMapCreatedShouldHaveProperStatusCode() {

	}
	
	@After
	public void tearDown() {
		
	}
	
	private void initEntities() {
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		DataInjector<EngineEntity> dataInjector = new DataInjector<EngineEntity>();

		entityFactory = new DomainEntityFactory();
		mediator = new ScenarioMediator();
		
		mediator.setParamLoader(paramLoader);
		mediator.setAuthenticator(authenticator);
		mediator.setEngineInjector(dataInjector);
	}
}
