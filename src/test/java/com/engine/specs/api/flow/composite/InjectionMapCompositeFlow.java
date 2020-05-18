package com.engine.specs.api.flow.composite;

import java.util.ArrayList;
import java.util.List;

import com.engine.specs.api.entity.EcuEntity;
import com.engine.specs.api.entity.EngineEntity;
import com.engine.specs.api.entity.InjectionMapEntity;
import com.engine.specs.api.entity.User;
import com.engine.specs.api.entity.WarningPresetEntity;
import com.engine.specs.api.entity.factory.DomainEntityFactory;
import com.engine.specs.api.mediator.ScenarioMediator;

public class InjectionMapCompositeFlow implements FeatureFlow<EcuEntity> {
	private List<FeatureFlow<?>> childEntities = new ArrayList<FeatureFlow<?>>();
	private DomainEntityFactory factory;
	//TODO thrown an exception if mediator is passed without previos steps (auth, etc)
	private ScenarioMediator mediator;
	private String entityRepr;
	private String resource;
	private InjectionMapEntity entity;
	
    public InjectionMapCompositeFlow addChildEntity(FeatureFlow<?> entity) {
    	childEntities.add(entity);
    	return this;
    }
    
	@Override
	public InjectionMapCompositeFlow usingFactory(DomainEntityFactory factory) {
		this.factory = factory;
    	return this;
	}

	@Override
	public InjectionMapCompositeFlow coordinateWith(ScenarioMediator mediator) {
		this.mediator = mediator;
		return this;
	}

	@Override
	public InjectionMapCompositeFlow getParameterizedResource(String name) {
		//TODO thrown an exception if mediator is not set.
		this.resource = mediator.commonParams().getProperty(name);
		return this;
	}

	@Override
	public InjectionMapCompositeFlow defineEntityRepr(String name) {
		this.entityRepr = name;
		return this;
	}

	@Override
	public InjectionMapCompositeFlow createInstance() {
		entity = factory.createEntity(entityRepr).getInjectionMap();
		return this;
	}
	
	public InjectionMapCompositeFlow injectChildren() {
		//TODO thrown an exception if createInstance was not called due entity object must be instanciated beforehand.
		EcuEntity ecu = null;
		User user = null;
		
		for(FeatureFlow<?> currentEntity : childEntities) {
			if(currentEntity.getType().contains("ecu")) {
				ecu = (EcuEntity) currentEntity.usingFactory(factory).createInstance().getEntity();
				mediator.inject(ecu, currentEntity.getResource());
			}
			
			if(currentEntity.getType().contains("user")) {
				user = (User) currentEntity.createInstance().getEntity();
			}
		}
		
		entity.setEcu(ecu);
		entity.setUser(user);
		
		return this;
	}

	@Override
	public String inject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EcuEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
