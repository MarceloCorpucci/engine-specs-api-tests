package com.engine.specs.api.flow.composite;

import java.util.List;
import java.util.Map;

import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.WarningPresetEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class WarningPresetCompositeFlow implements FeatureFlow {
	private List<FeatureFlow> childEntities;
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String paramName;
	private Map<String, String> param;
	private WarningPresetEntity entity;
	
    public void addChildEntity(FeatureFlow entity) {
    	childEntities.add(entity);
    }

	@Override
	public FeatureFlow usingFactory(DomainEntityFactory factory) {
		this.factory = factory;
		return this;
	}

	@Override
	public FeatureFlow injectingThrough(ScenarioMediator mediator) {
		this.mediator = mediator;
		return this;
	}
	
	@Override
	public FeatureFlow defineParam(String paramName) {
		this.paramName = paramName;
		return this;
	}
	
	@Override
	public FeatureFlow as(String paramValue) {
		//TODO thrown an exception if paramName is not defined.
		this.param.put(paramName, paramValue);
		return this;
	}
	
	@Override
	public FeatureFlow createInstance() {
		entity = factory
					.createEntity(param.get("entityType"))
					.getWarningPreset();
		return this;
	}
	
	@Override
	public String inject() {	
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "WarnPreset".
		EngineEntity engine = null;
		
		for(FeatureFlow entity : childEntities) {
			if(entity.getType().contains("engine")) {
				engine = entity.createInstance().getEntity();
				mediator.inject(engine, entity.getResource());
			}
		}
		
		WarningPresetEntity warnPreset = factory
											.createEntity(param.get("entityType"))
											.getWarningPreset();
		warnPreset.setEngine(engine);
		
		return mediator.inject(warnPreset, param.get("resource"));
	}

	@Override
	public String getType() {
		return param.get("entityType");
	}

	@Override
	public String getResource() {
		return param.get("resource");
	}
	
	public WarningPresetEntity getEntity() {
		return factory.getWarningPreset();
	}

}
