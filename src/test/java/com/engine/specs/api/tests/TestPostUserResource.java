package com.engine.specs.api.tests;

import static io.restassured.RestAssured.given;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.UserEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.flow.composite.UserLeafFlow;
import com.engine.specs.api.mediator.ScenarioMediator;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.ParamLoader;

import io.restassured.response.Response;

public class TestPostUserResource {
	private ScenarioMediator mediator;
	private DomainEntityFactory entityFactory;
	private UserLeafFlow userFlow;
	private UserEntity user;
	private String userId;

	@Before
	public void setUp() {
		this.initEntities();
		
		user = userFlow
				.coordinateWith(mediator)
				.getParameterizedResource("userResource")
				.defineEntityRepr("user_default")
				.usingFactory(entityFactory)
				.createInstance()
				.getEntity();
	}
	
	@Test
	public void userCreationShouldReturnProperStatusCode() {
		Response response = given()
								.contentType("application/json")
								.header("Authorization", "Bearer " + mediator.authenticate())
								.body(user)
							.when()
								.post(mediator.testParams().getProperty("endPoint") + userFlow.getResource());
		
		userId = response
					.jsonPath()
					.getString(
					String.format("%s", "user._id.$oid"));
		
		response.then()
					.assertThat()
					.statusCode(201);
	}
	
	@After
	public void tearDown() {
//		mediator.cleanUp(userId, userFlow.getResource());
		mediator.cleanUp("user", user.getEmail(), userFlow.getResource());
	}
	
	private void initEntities() {
		System.setProperty("envProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/test.properties");
		System.setProperty("commonProperties", "/Users/marcelocorpucci/Repositories/engine-specs-api-tests/src/test/resources/env/config/common.properties");
		
		entityFactory = new DomainEntityFactory();
		ParamLoader paramLoader = new ParamLoader();
		Authenticator authenticator = new Authenticator();
		DataCleaner dataCleaner = new DataCleaner();
		
		mediator = new ScenarioMediator();
		mediator.setParamLoader(paramLoader);
		mediator.setAuthenticator(authenticator);
		mediator.setDataCleaner(dataCleaner);
		
		userFlow = new UserLeafFlow();
	}
}
