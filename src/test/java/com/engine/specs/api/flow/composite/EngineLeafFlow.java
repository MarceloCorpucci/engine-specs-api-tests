package com.engine.specs.api.flow.composite;

import java.util.Map;

import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class EngineLeafFlow implements FeatureFlow {
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String paramName;
	private Map<String, String> param;
	private EngineEntity entity;
	
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
					.getEngine();
		return this;
	}

	@Override
	public String inject() {	
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "engine".
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

	public EngineEntity getEntity() {
		return factory.getEngine();
	}

}
