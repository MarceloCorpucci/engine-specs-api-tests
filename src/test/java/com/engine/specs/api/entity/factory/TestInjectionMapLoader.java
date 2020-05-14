package com.engine.specs.api.entity.factory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.engine.specs.api.entity.InjectionMapEntity;

public class TestInjectionMapLoader {
	private InjectionMapEntity entity;
	private InjectionMapLoader loader;
	
	@Before
	public void setUp() {
		loader = new InjectionMapLoader();
	}
	
	@Test
	public void injectionMapLoaderShouldWorkFine() {
		entity = loader.get("injection_map_default").fromJsonResource();
		
		List<Float> floatList = new ArrayList<Float>();
		floatList.add(1.2f);
		floatList.add(2.3f);
		floatList.add(3.4f);
		
		assertThat(entity.getMap().get(0).get("750"), equalTo(floatList));
	}

}
