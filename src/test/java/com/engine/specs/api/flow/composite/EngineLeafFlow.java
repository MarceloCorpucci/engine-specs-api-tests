package com.engine.specs.api.flow.composite;

import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class EngineLeafFlow implements FeatureFlow<EngineEntity> {
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String entityRepr;
	private String resource;
	private EngineEntity entity;
	
	@Override
	public EngineLeafFlow usingFactory(DomainEntityFactory factory) {
		this.factory = factory;
		return this;
	}

	@Override
	public EngineLeafFlow coordinateWith(ScenarioMediator mediator) {
		this.mediator = mediator;
		return this;
	}

	@Override
	public EngineLeafFlow getParameterizedResource(String name) {
		//TODO thrown an exception if mediator is not set.
		this.resource = mediator.commonParams().getProperty(name);
		return this;
	}
	
	@Override
	public EngineLeafFlow defineEntityRepr(String name) {
		this.entityRepr = name;
		return this;
	}
	
	@Override
	public EngineLeafFlow createInstance() {
		entity = factory.createEntity(entityRepr).getEngine();
		return this;
	}

	@Override
	public String inject() {	
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "engine".
		return mediator.inject(entity, resource);
	}

	@Override
	public String getType() {
		return entityRepr;
	}

	@Override
	public String getResource() {
		return resource;
	}

	public EngineEntity getEntity() {
		return entity;
	}

}
