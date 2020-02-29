package com.engine.specs.api.entity.factory;

public class EngineFactory {
	public Engine getEngine(String reprType) {
		if (reprType == "min") {
			return new EngineMinRepresentation();
		}
		
		if (reprType == "full") {
			return new EngineFullRepresentation();
		}
		
		return null;
	}

}
