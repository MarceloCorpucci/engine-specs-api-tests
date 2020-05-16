package com.engine.specs.api.entity.factory;

import com.engine.specs.api.entity.EcuEntity;
import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.InjectionMapEntity;
import com.engine.specs.api.entity.WarningPresetEntity;

public class DomainEntityFactory {
	private String type;
	private EngineLoader engineLoader;
	private WarningPresetLoader warningPresetLoader;
	private EcuLoader ecuLoader;
	private InjectionMapLoader injectionMapLoader;
	
	public DomainEntityFactory createEntity(String type) {
		this.type = type;
		
		if (type.contains("engine")) {
			engineLoader =  new EngineLoader();
		}
		
		if(type.contains("warning")) {
			warningPresetLoader = new WarningPresetLoader();
		}
		
		if(type.contains("ecu")) {
			ecuLoader = new EcuLoader();
		}
		
		if(type.contains("injection")) {
			injectionMapLoader = new InjectionMapLoader();
		}
		
		return this;
	}
	
	public EngineEntity getEngine() {
		return engineLoader.get(type).fromJsonResource();
	}
	
	public WarningPresetEntity getWarningPreset() {
		return warningPresetLoader.get(type).fromJsonResource();
	}
	
	public EcuEntity getEcu() {
		return ecuLoader.get(type).fromJsonResource();
	}

	public InjectionMapEntity getInjectionMap() {
		return injectionMapLoader.get(type).fromJsonResource();
	}
}
