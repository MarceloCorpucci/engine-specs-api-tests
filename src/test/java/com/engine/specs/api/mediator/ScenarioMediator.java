package com.engine.specs.api.mediator;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class ScenarioMediator {
	private ParamLoader paramLoader;
	private Authenticator authenticator;
	private DataInjector dataInjector;
	private DataCleaner dataCleaner;
	
	public void setParamLoader(ParamLoader paramLoader) {
		this.paramLoader = paramLoader;
	}
	
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
		this.authenticator.setMediator(this);
	}

	public void setDataInjector(DataInjector dataInjector) {
		this.dataInjector = dataInjector;
		this.dataInjector.setMediator(this);
	}

	public void setDataCleaner(DataCleaner dataCleaner) {
		this.dataCleaner = dataCleaner;
	}
	
	public String authenticate() {
		return this.authenticator
						.getToken()
						.basedOn(paramLoader.properties().getProperty("email"))
						.and(paramLoader.properties().getProperty("password"))
						.against(paramLoader.properties().getProperty("endPoint"));
		
	}
	
	//Change to Generics
	public String inject(Engine engine) {
		return this.dataInjector.inject(engine);
	}
	
}
