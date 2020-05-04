package com.engine.specs.api.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.entity.factory.EcuEntity;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.entity.factory.User;
import com.engine.specs.api.entity.factory.WarningPresetEntity;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataExplorer;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class TestPostInjectionMap {
	private DomainEntityFactory entityFactory;
	
	private EngineEntity engine;
	private WarningPresetEntity warningPreset;
	private EcuEntity ecu;
	
	private ScenarioMediator mediator;
	
	private String engineResource;
	private String engineId;
	private String warnPresetResource;
	private String warnPresetId;
	private String ecuResource;
	private String ecuId;
	
	@Before
	public void setUp() {
		this.initEntities();
		
		engine = entityFactory
					.createEntity("engine_min_repr")
					.getEngine();
		
		engineResource = mediator.commonParams().getProperty("engineResource");
		engineId = mediator.inject(engine, engineResource);
		
		EngineEntity savedEngine = mediator.retrieveResource(new EngineEntity(), engineResource + "/" + engineId);
		
		warningPreset = entityFactory
							.createEntity("warning_preset_default")
							.getWarningPreset();
		
		warningPreset.setEngine(savedEngine);
		
		warnPresetResource = mediator.commonParams().getProperty("warnPresetResource");
		warnPresetId = mediator.inject(warningPreset, warnPresetResource);
		
		WarningPresetEntity savedWarnPreset = mediator.retrieveResource(new WarningPresetEntity(), warnPresetResource + "/" + warnPresetId);
		
		ecu = entityFactory
				.createEntity("ecu_default")
				.getEcu();
		
		ecu.setEngine(savedEngine);
		ecu.setWarningPreset(savedWarnPreset);
		User user = new User();
		user.setEmail(mediator.testParams().getProperty("email"));
		ecu.setUser(user);
		
		ecuResource = mediator.commonParams().getProperty("ecuResource");
		ecuId = mediator.inject(ecu, ecuResource);
		
		//EcuEntity savedEcu = mediator.retrieveResource(entity, resource);
	}
	
	@Test
	public void injectionMapCreatedShouldHaveProperStatusCode() {

	}
	
	@After
	public void tearDown() {
		mediator.cleanUp("name", warningPreset.getName(), warnPresetResource);
		mediator.cleanUp("model", engine.getModel(), engineResource);
	}
	
	private void initEntities() {
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		System.setProperty("commonProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/common.properties");
		
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		
		DataInjector<EngineEntity> engineInjector = new DataInjector<EngineEntity>();
		DataInjector<WarningPresetEntity> warnPresetInjector = new DataInjector<WarningPresetEntity>();
		DataInjector<EcuEntity> ecuInjector = new DataInjector<EcuEntity>();
		
		DataExplorer<EngineEntity> engineExplorer = new DataExplorer<EngineEntity>();
		DataExplorer<WarningPresetEntity> warnPresetExplorer = new DataExplorer<WarningPresetEntity>();
		DataExplorer<EcuEntity> ecuExplorer = new DataExplorer<EcuEntity>();
		
		DataCleaner dataCleaner = new DataCleaner();
		
		entityFactory = new DomainEntityFactory();
		mediator = new ScenarioMediator();
		
		mediator.setParamLoader(paramLoader);
		mediator.setAuthenticator(authenticator);
		mediator.setEngineInjector(engineInjector);
		mediator.setWarnPresetInjector(warnPresetInjector);
		mediator.setEcuInjector(ecuInjector);
		mediator.setEngineExplorer(engineExplorer);
		mediator.setWarnPresetExplorer(warnPresetExplorer);
		mediator.setEcuExplorer(ecuExplorer);
		mediator.setDataCleaner(dataCleaner);
	}
}
