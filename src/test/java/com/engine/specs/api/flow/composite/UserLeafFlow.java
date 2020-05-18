package com.engine.specs.api.flow.composite;

import com.engine.specs.api.entity.User;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class UserLeafFlow implements FeatureFlow<User> {
//	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String entityRepr;
	private String resource;
	private User entity;

	@Override
	public UserLeafFlow usingFactory(DomainEntityFactory factory) {
//		this.factory = factory;
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
		//TODO Implement a factory for Users.
		entity.setEmail(mediator.testParams().getProperty("email"));
		return this;
	}

	@Override
	public String inject() {
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "engine".
		
		// return mediator.inject(entity, resource);
		return "";
	}

	@Override
	public String getType() {
		return entityRepr;
	}

	@Override
	public String getResource() {
		return resource;
	}

	public User getEntity() {
		return entity;
	}
}
