package com.engine.specs.api.flow.composite;

import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public interface FeatureFlow {
	FeatureFlow usingFactory(DomainEntityFactory factory);
	FeatureFlow injectingThrough(ScenarioMediator mediator);
	FeatureFlow defineParam(String paramName);
	FeatureFlow as(String paramValue);
	FeatureFlow createInstance();
	String inject();
	String getType();
	String getResource();
}
