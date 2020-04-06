package com.engine.specs.api.entity.factory;

public class DomainEntityFactory {
	private String type;
	private EngineLoader engineLoader;
	private EcuLoader ecuLoader;
	
	public DomainEntityFactory createEntity(String type) {
		this.type = type;
		
		if (type.equals("engine_full_repr") || type.equals("engine_min_repr")) {
			engineLoader =  new EngineLoader();
		}
		
		if(type.equals("ecu_default")) {
			ecuLoader = new EcuLoader();
		}
		
		return this;
	}
	
	public EngineEntity getEngine() {
		return engineLoader.get(type).fromJsonResource();
	}
	
	public EcuEntity getEcu() {
		return ecuLoader.get(type).fromJsonResource();
	}
	
//	public <T> T createEntity(String type) {
//		T entity;
//		if (type.equals("engine_full_repr") || type.equals("engine_min_repr")) {
//			entity =  new EngineLoader();
//		}
//		
//		if(type.equals("ecu_default")) {
//			ecuLoader = new EcuLoader();
//		}
//		
//	    return list.get(0);
//	}
}
