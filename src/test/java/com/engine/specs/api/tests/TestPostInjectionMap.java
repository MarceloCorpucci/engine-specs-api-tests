package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.EcuEntity;
import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.InjectionMapEntity;
import com.engine.specs.api.entity.UserEntity;
import com.engine.specs.api.entity.WarningPresetEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.flow.composite.EcuCompositeFlow;
import com.engine.specs.api.flow.composite.EngineLeafFlow;
import com.engine.specs.api.flow.composite.InjectionMapCompositeFlow;
import com.engine.specs.api.flow.composite.UserLeafFlow;
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
	private UserLeafFlow userFlow;
	private EcuCompositeFlow ecuFlow;
	private InjectionMapCompositeFlow injectionMapFlow;
	
	private DomainEntityFactory entityFactory;
	
	private EngineEntity engine;
	private WarningPresetEntity warningPreset;
	private EcuEntity ecu;
	private InjectionMapEntity injectionMap;
	
	private ScenarioMediator mediator;
	
	private RequestSpecification request;
	
	@Before
	public void setUp() {
		this.initEntities();
		
		engineFlow
			.coordinateWith(mediator)
			.getParameterizedResource("engineResource")
			.defineEntityRepr("engine_min_repr");

		warnPresetFlow
			.coordinateWith(mediator)
			.getParameterizedResource("warnPresetResource")
			.defineEntityRepr("warning_preset_default");
		
		userFlow
			.coordinateWith(mediator)
			.getParameterizedResource("userResource")
			.defineEntityRepr("user_default");
		
		ecuFlow
			.coordinateWith(mediator)
			.getParameterizedResource("ecuResource")
			.defineEntityRepr("ecu_default");
		
		injectionMap = injectionMapFlow
							.coordinateWith(mediator)
							.getParameterizedResource("injectionMapResource")
							.defineEntityRepr("injection_map_default")
							.addChildEntity(engineFlow)
							.addChildEntity(warnPresetFlow)
							.addChildEntity(userFlow)
							.addChildEntity(ecuFlow)
							.usingFactory(entityFactory)
							.createInstance()
							.injectChildren()
							.getEntity();
		
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
				.post(mediator.testParams().getProperty("endPoint") + injectionMapFlow.getResource())
			.then()
				.assertThat()
				.statusCode(201)
				.log()
				.all();
	}
	
	@After
	public void tearDown() {
		mediator.cleanUp("ecu", injectionMap.getEcu().getModel(), injectionMapFlow.getResource());
		mediator.cleanUp("model", ecu.getModel(), ecuFlow.getResource());
		mediator.cleanUp("name", warningPreset.getName(), warnPresetFlow.getResource());
		mediator.cleanUp("model", engine.getModel(), engineFlow.getResource());
//		mediator.cleanUp("USER!!!", engine.getModel(), engineFlow.getResource());
	}
	
	private void initEntities() {
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		System.setProperty("commonProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/common.properties");
		
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		
		engineFlow = new EngineLeafFlow();
		warnPresetFlow = new WarningPresetCompositeFlow();
		userFlow = new UserLeafFlow();
		ecuFlow = new EcuCompositeFlow();
		injectionMapFlow = new InjectionMapCompositeFlow();
		
		DataInjector<UserEntity> userInjector = new DataInjector<UserEntity>();
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
		mediator.setUserInjector(userInjector);
		mediator.setEngineInjector(engineInjector);
		mediator.setWarnPresetInjector(warnPresetInjector);
		mediator.setEcuInjector(ecuInjector);
		mediator.setEngineExplorer(engineExplorer);
		mediator.setWarnPresetExplorer(warnPresetExplorer);
		mediator.setEcuExplorer(ecuExplorer);
		mediator.setDataCleaner(dataCleaner);
	}
}
