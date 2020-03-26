package com.engine.specs.api.mediator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataInjector;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ScenarioMediator {
	private Authenticator authenticator = new Authenticator();
	private DataInjector dataInjector;
	private DataCleaner dataCleaner;
	
	public Properties loadTestParams() throws IOException {
		String resourcePath = String.valueOf(System.getProperty("envProperties"));
		InputStream file = new FileInputStream(resourcePath);
		Properties testParams = new Properties();
		testParams.load(file);
		
		return testParams;
	}
	
	public RequestSpecification request() throws IOException {
		RestAssured.baseURI = loadTestParams().getProperty("endPoint");
		
		return RestAssured.requestSpecification;
	}
	
	public String authenticate() throws IOException {
		return authenticator
					.getToken()
					.basedOn(loadTestParams().getProperty("email"))
					.and(loadTestParams().getProperty("password"));
		
	}
	
	
}
