package com.engine.specs.api.flow.composite;

import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public interface FeatureFlow<T> {
	FeatureFlow<T> usingFactory(DomainEntityFactory factory);
	FeatureFlow<T> injectingThrough(ScenarioMediator mediator);
	FeatureFlow<T> defineParam(String paramName);
	FeatureFlow<T> as(String paramValue);
	FeatureFlow<T> createInstance();
	String inject();
	String getType();
	String getResource();
	T getEntity();
}
