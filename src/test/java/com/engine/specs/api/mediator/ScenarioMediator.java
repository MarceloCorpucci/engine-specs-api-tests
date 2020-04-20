package com.engine.specs.api.mediator;

import java.util.Properties;

import com.engine.specs.api.entity.builder.Engine;
import com.engine.specs.api.entity.factory.EcuEntity;
import com.engine.specs.api.entity.factory.EngineEntity;
import com.engine.specs.api.entity.factory.WarningPresetEntity;
import com.engine.specs.api.mediator.component.Authenticator;
import com.engine.specs.api.mediator.component.DataCleaner;
import com.engine.specs.api.mediator.component.DataExplorer;
import com.engine.specs.api.mediator.component.DataInjector;
import com.engine.specs.api.mediator.component.ParamLoader;

public class ScenarioMediator {
	private ParamLoader paramLoader;
	private Authenticator authenticator;
	private DataInjector<Engine> dataInjector;
	private DataInjector<EngineEntity> engineInjector;
	private DataInjector<WarningPresetEntity> warnPresetInjector;
	private DataInjector<EcuEntity> ecuInjector;
	private DataExplorer dataExplorer;
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

	public void setWarnPresetInjector(DataInjector<WarningPresetEntity> warnPresetInjector) {
		this.warnPresetInjector = warnPresetInjector;
		this.warnPresetInjector.setMediator(this);
	}
	
	public void setEcuInjector(DataInjector<EcuEntity> ecuInjector) {
		this.ecuInjector = ecuInjector;
		this.ecuInjector.setMediator(this);
	}
	
	public void setDataExplorer(DataExplorer dataExplorer) {
		this.dataExplorer = dataExplorer;
	}
	
	public void setDataCleaner(DataCleaner dataCleaner) {
		this.dataCleaner = dataCleaner;
		this.dataCleaner.setMediator(this);
	}
	
	public Properties testParams() {
		return paramLoader.properties();
	}
	
	public Properties commonParams() {
		return paramLoader.commons();
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
	
	public String inject(WarningPresetEntity entity, String resource) {
		return this.warnPresetInjector.inject(entity).in(resource);
	}

	public String inject(EcuEntity entity, String resource) {
		return this.ecuInjector.inject(entity).in(resource);
	}	
	
	public int cleanUp(String id, String resource) {
		return this.dataCleaner.cleanUp(id, resource);
	}
	
	public Object basedOnEntityRetrieveResource(EngineEntity entity, String resource) {
//		return this.dataExplorer.basedOn(entity).retrieve(resource);
		return this.dataExplorer.retrieve(entity, resource);
	}
	
	public int cleanUp(String property, String value, String resource) {
		return this.dataCleaner.cleanUp(property, value, resource);
	}
}
