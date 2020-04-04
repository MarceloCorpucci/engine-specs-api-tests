package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EcuLoader implements EntityLoader<Ecu> {
	private String type;

	public EntityLoader<Ecu> get(String type) {
		this.type = type;
		return this;
	}

	public Ecu fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       Ecu ecu = new Ecu();
       
       URL resource = classLoader.getResource("data/ecu/ecu_" + type  + ".json");
        
       try {
    	   ecu = objectMapper.readValue(new File(resource.getFile()), Ecu.class);
		
       } catch (JsonParseException e) {
    	   e.printStackTrace();
		
       } catch (JsonMappingException e) {
    	   e.printStackTrace();
		
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       
       return ecu;
	}

}
