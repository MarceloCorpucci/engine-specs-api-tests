package com.engine.specs.api.flow.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.WarningPresetEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class WarningPresetCompositeFlow implements FeatureFlow<WarningPresetEntity> {
	private List<FeatureFlow<?>> childEntities = new ArrayList<FeatureFlow<?>>();
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String paramName;
	private Map<String, String> param = new HashMap<String, String>();;
	private WarningPresetEntity entity;
	
    public WarningPresetCompositeFlow addChildEntity(FeatureFlow<?> entity) {
    	childEntities.add(entity);
    	return this;
    }

	@Override
	public WarningPresetCompositeFlow usingFactory(DomainEntityFactory factory) {
		this.factory = factory;
		return this;
	}

	@Override
	public WarningPresetCompositeFlow injectingThrough(ScenarioMediator mediator) {
		this.mediator = mediator;
		return this;
	}
	
	@Override
	public WarningPresetCompositeFlow defineParam(String paramName) {
		this.paramName = paramName;
		return this;
	}
	
	@Override
	public WarningPresetCompositeFlow as(String paramValue) {
		//TODO thrown an exception if paramName is not defined.
		this.param.put(paramName, paramValue);
		return this;
	}
	
	@Override
	public WarningPresetCompositeFlow createInstance() {
		entity = factory
					.createEntity(param.get("entityType"))
					.getWarningPreset();
		return this;
	}
	
	public WarningPresetCompositeFlow injectChildren() {
		//TODO thrown an exception if createInstance was not called due entity object must be instanciated beforehand.
		EngineEntity engine = null;
		
		for(FeatureFlow<?> currentEntity : childEntities) {
			if(currentEntity.getType().contains("engine")) {
				engine = (EngineEntity) currentEntity.usingFactory(factory).createInstance().getEntity();
				mediator.inject(engine, currentEntity.getResource());
			}
		}
		
		entity.setEngine(engine);
		
		return this;
	}
	
	@Override
	public String inject() {	
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "WarnPreset".		
		return mediator.inject(entity, param.get("resource"));
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
		return entity;
	}

}
