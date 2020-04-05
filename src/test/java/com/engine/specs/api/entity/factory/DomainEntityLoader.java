package com.engine.specs.api.entity.factory;

public interface DomainEntityLoader<T> {
	DomainEntityLoader<T> get(String type);
	T fromJsonResource();
}
