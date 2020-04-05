package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EngineLoader implements DomainEntityLoader<EngineEntity> {
	private String type;
	
	public DomainEntityLoader<EngineEntity> get(String type) {
		this.type = type;
		return this;
	}
	
	public EngineEntity fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       EngineEntity engine = new EngineEntity();
       
       URL resource = classLoader.getResource("data/engine/" + type  + ".json");
        
       try {
    	   engine = objectMapper.readValue(new File(resource.getFile()), EngineEntity.class);
		
       } catch (JsonParseException e) {
    	   e.printStackTrace();
		
       } catch (JsonMappingException e) {
    	   e.printStackTrace();
		
       } catch (IOException e) {
    	   e.printStackTrace();
       }
        
       return engine;
	}

}
