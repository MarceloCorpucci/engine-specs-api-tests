package com.engine.specs.api.flow.composite;

import java.util.ArrayList;
import java.util.List;

import com.engine.specs.api.entity.EcuEntity;
import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.User;
import com.engine.specs.api.entity.WarningPresetEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class EcuCompositeFlow implements FeatureFlow<EcuEntity> {
	private List<FeatureFlow<?>> childEntities = new ArrayList<FeatureFlow<?>>();
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String entityRepr;
	private String resource;
	private EcuEntity entity;

    public EcuCompositeFlow addChildEntity(FeatureFlow<?> entity) {
    	childEntities.add(entity);
    	return this;
    }
	
	@Override
	public EcuCompositeFlow usingFactory(DomainEntityFactory factory) {
		this.factory = factory;
    	return this;
	}

	@Override
	public EcuCompositeFlow coordinateWith(ScenarioMediator mediator) {
		this.mediator = mediator;
		return null;
	}

	@Override
	public EcuCompositeFlow getParameterizedResource(String name) {
		//TODO thrown an exception if mediator is not set.
		this.resource = mediator.commonParams().getProperty(name);
		return null;
	}

	@Override
	public EcuCompositeFlow defineEntityRepr(String name) {
		this.entityRepr = name;
		return null;
	}

	@Override
	public EcuCompositeFlow createInstance() {
		entity = factory.createEntity(entityRepr).getEcu();
		return null;
	}
	
	public EcuCompositeFlow injectChildren() {
		//TODO thrown an exception if createInstance was not called due entity object must be instanciated beforehand.
		EngineEntity engine = null;
		WarningPresetEntity warningPreset = null;
		User user = null;
		
		for(FeatureFlow<?> currentEntity : childEntities) {
			if(currentEntity.getType().contains("engine")) {
				engine = (EngineEntity) currentEntity.usingFactory(factory).createInstance().getEntity();
				mediator.inject(engine, currentEntity.getResource());
			}
			
			if(currentEntity.getType().contains("warning")) {
				warningPreset = (WarningPresetEntity) currentEntity.usingFactory(factory).createInstance().getEntity();
				mediator.inject(warningPreset, currentEntity.getResource());
			}
			
			if(currentEntity.getType().contains("user")) {
				user = (User) currentEntity.createInstance().getEntity();
			}
		}
		
		entity.setEngine(engine);
		entity.setWarningPreset(warningPreset);
		entity.setUser(user);
		
		return this;
	}

	@Override
	public String inject() {
		//TODO thrown an exception if previous methods were not called.
		//TODO: Thrown an exception if the resource is not "WarnPreset".		
		return mediator.inject(entity, resource);
	}

	@Override
	public String getType() {
		return entityRepr;
	}

	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EcuEntity getEntity() {
		return entity;
	}

}
