package com.engine.specs.api.entity.factory;

public interface EntityLoader<T> {
	EntityLoader<T> get(String type);
	T fromJsonResource();
}
