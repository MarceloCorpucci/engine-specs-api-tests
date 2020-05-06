package com.engine.specs.api.entity.factory;

public class DomainEntityFactory {
	private String type;
	private EngineLoader engineLoader;
	private WarningPresetLoader warningPresetLoader;
	private EcuLoader ecuLoader;
	private InjectionMapLoader injectionMapLoader;
	
	public DomainEntityFactory createEntity(String type) {
		this.type = type;
		
		if (type.equals("engine_full_repr") || type.equals("engine_min_repr")) {
			engineLoader =  new EngineLoader();
		}
		
		if(type.equals("warning_preset_default")) {
			warningPresetLoader = new WarningPresetLoader();
		}
		
		if(type.equals("ecu_default")) {
			ecuLoader = new EcuLoader();
		}
		
		if(type.equals("injection_map_default")) {
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
