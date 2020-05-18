package com.engine.specs.api.flow.composite;

import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public interface FeatureFlow<T> {
	FeatureFlow<T> usingFactory(DomainEntityFactory factory);
	FeatureFlow<T> coordinateWith(ScenarioMediator mediator);
	FeatureFlow<T> getParameterizedResource(String name);
	FeatureFlow<T> defineEntityRepr(String name);
	FeatureFlow<T> createInstance();
	String inject();
	String getType();
	String getResource();
	T getEntity();
}
