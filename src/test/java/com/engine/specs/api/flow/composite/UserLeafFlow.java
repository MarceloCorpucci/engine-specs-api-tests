package com.engine.specs.api.flow.composite;

import com.engine.specs.api.entity.UserEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class UserLeafFlow implements FeatureFlow<UserEntity> {
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String entityRepr;
	private String resource;
	private UserEntity entity;

	@Override
	public UserLeafFlow usingFactory(DomainEntityFactory factory) {
		this.factory = factory;
		return this;
	}

	@Override
	public UserLeafFlow coordinateWith(ScenarioMediator mediator) {
		this.mediator = mediator;
		return this;
	}

	@Override
	public UserLeafFlow getParameterizedResource(String name) {
		//TODO thrown an exception if mediator is not set.
		this.resource = mediator.commonParams().getProperty(name);
		return this;
	}

	@Override
	public UserLeafFlow defineEntityRepr(String name) {
		this.entityRepr = name;
		return this;
	}

	@Override
	public UserLeafFlow createInstance() {
		entity = factory.createEntity(entityRepr).getUser();
		return this;
	}

	@Override
	public String inject() {
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "user".
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

	public UserEntity getEntity() {
		return entity;
	}
}
