package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InjectionMapLoader implements DomainEntityLoader<InjectionMapEntity> {
	private String type;

	@Override
	public DomainEntityLoader<InjectionMapEntity> get(String type) {
		this.type = type;
		return this;
	}

	@Override
	public InjectionMapEntity fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       InjectionMapEntity injectionMap = new InjectionMapEntity();
       
       URL resource = classLoader.getResource("data/ecu/" + type  + ".json");
        
       try {
    	   injectionMap = objectMapper.readValue(new File(resource.getFile()), InjectionMapEntity.class);
		
       } catch (JsonParseException e) {
    	   e.printStackTrace();
		
       } catch (JsonMappingException e) {
    	   e.printStackTrace();
		
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       
       return injectionMap;
	}

}
