package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.EcuEntity;
import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.InjectionMapEntity;
import com.engine.specs.api.entity.User;
import com.engine.specs.api.entity.WarningPresetEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.flow.composite.EngineLeafFlow;
import com.engine.specs.api.flow.composite.WarningPresetCompositeFlow;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataExplorer;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

import io.restassured.specification.RequestSpecification;

public class TestPostInjectionMap {
	private EngineLeafFlow engineFlow;
	private WarningPresetCompositeFlow warnPresetFlow;

	private DomainEntityFactory entityFactory;
	
	private EngineEntity engine;
	private WarningPresetEntity warningPreset;
	private EcuEntity ecu;
	private InjectionMapEntity injectionMap;
	
	private ScenarioMediator mediator;
	
	private String engineResource;
	private String warnPresetResource;
	private String ecuResource;
	private String injectionMapResource;
	
	private RequestSpecification request;
	
	@Before
	public void setUp() {
		this.initEntities();
		
//		engine = entityFactory
//					.createEntity("engine_min_repr")
//					.getEngine();
//		
//		engineResource = mediator.commonParams().getProperty("engineResource");
//		mediator.inject(engine, engineResource);
//		
//		warningPreset = entityFactory
//							.createEntity("warning_preset_default")
//							.getWarningPreset();
//		
//		warningPreset.setEngine(engine);
//		
//		warnPresetResource = mediator.commonParams().getProperty("warnPresetResource");
//		mediator.inject(warningPreset, warnPresetResource);
//		
//		ecu = entityFactory
//				.createEntity("ecu_default")
//				.getEcu();
//		
//		ecu.setEngine(engine);
//		ecu.setWarningPreset(warningPreset);
//		User user = new User();
//		user.setEmail(mediator.testParams().getProperty("email"));
//		ecu.setUser(user);
//		
//		ecuResource = mediator.commonParams().getProperty("ecuResource");
//		mediator.inject(ecu, ecuResource);
//		
//		injectionMapResource = mediator.commonParams().getProperty("injectionMapResource");
//		injectionMap = entityFactory
//							.createEntity("injection_map_default")
//							.getInjectionMap();
//		
//		injectionMap.setEcu(ecu);
//		injectionMap.setUser(user);
		
		engineFlow
			.defineEntityRepr("entityType").getParameterizedResource("engine_min_repr")
			.defineEntityRepr("resource").getParameterizedResource(mediator
											.commonParams()
											.getProperty("engineResource"));

		warnPresetFlow
			.defineEntityRepr("entityType").getParameterizedResource("warning_preset_default")
			.defineEntityRepr("resource").getParameterizedResource(mediator
											.commonParams()
											.getProperty("warnPresetResource"));
		
	
//		warnPreset = warnPresetFlow
//						.defineParam("entityType").as("warning_preset_default")
//						.defineParam("resource").as(warnPresetResource)
//						.addChildEntity(engineFlow)
//						.usingFactory(entityFactory)
//						.injectingThrough(mediator)
//						.createInstance()
//						.injectChildren()
//						.getEntity();
		
		request = given()
					.contentType("application/json")
					.header("Authorization", "Bearer " + mediator.authenticate())
					.body(injectionMap)
					.log()
					.all();
	}
	
	@Test
	public void injectionMapCreatedShouldHaveProperStatusCode() {
		request
			.when()
				.post(mediator.testParams().getProperty("endPoint") + injectionMapResource)
			.then()
				.assertThat()
				.statusCode(201)
				.log()
				.all();
	}
	
	@After
	public void tearDown() {
		mediator.cleanUp("ecu", injectionMap.getEcu().getModel(), injectionMapResource);
		mediator.cleanUp("model", ecu.getModel(), ecuResource);
		mediator.cleanUp("name", warningPreset.getName(), warnPresetResource);
		mediator.cleanUp("model", engine.getModel(), engineResource);
	}
	
	private void initEntities() {
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		System.setProperty("commonProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/common.properties");
		
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		
		engineFlow = new EngineLeafFlow();
		warnPresetFlow = new WarningPresetCompositeFlow();
		
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
