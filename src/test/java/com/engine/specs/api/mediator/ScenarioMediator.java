package com.engine.specs.api.mediator;

import java.util.Properties;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class ScenarioMediator {
	private ParamLoader paramLoader;
	private Authenticator authenticator;
	private DataInjector<Engine> dataInjector;
	private DataInjector<EngineEntity> engineInjector;
	private DataCleaner dataCleaner;
	
	public void setParamLoader(ParamLoader paramLoader) {
		this.paramLoader = paramLoader;
	}
	
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
		this.authenticator.setMediator(this);
	}

	public void setDataInjector(DataInjector<Engine> dataInjector) {
		this.dataInjector = dataInjector;
		this.dataInjector.setMediator(this);
	}
	
	public void setEngineInjector(DataInjector<EngineEntity> dataInjector) {
		this.engineInjector = dataInjector;
		this.engineInjector.setMediator(this);
	}

	public void setDataCleaner(DataCleaner dataCleaner) {
		this.dataCleaner = dataCleaner;
		this.dataCleaner.setMediator(this);
	}
	
	public Properties testParams() {
		return paramLoader.properties();
	}
	
	public String authenticate() {
		return this.authenticator.authenticate();	
	}
	
	public String inject(Engine entity, String resource) {
		return this.dataInjector.inject(entity).in(resource);
	}
	
	public String inject(EngineEntity entity, String resource) {
		return this.engineInjector.inject(entity).in(resource);
	}
	
	public int cleanUp(String id, String resource) {
		return this.dataCleaner.cleanUp(id, resource);
	}
	
	public int cleanUp(String property, String value, String resource) {
		return this.dataCleaner.cleanUp(property, value, resource);
	}
}
