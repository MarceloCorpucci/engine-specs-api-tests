package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EngineLoader implements EntityLoader<Engine> {
	private String type;
	
	public EntityLoader<Engine> get(String type) {
		this.type = type;
		return this;
	}
	
	public Engine fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       Engine engine = new Engine();
       
       URL resource = classLoader.getResource("data/engine/engine_" + type  + ".json");
        
       try {
    	   engine = objectMapper.readValue(new File(resource.getFile()), Engine.class);
		
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
