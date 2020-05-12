package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.engine.specs.api.entity.EcuEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EcuLoader implements DomainEntityLoader<EcuEntity> {
	private String type;

	public DomainEntityLoader<EcuEntity> get(String type) {
		this.type = type;
		return this;
	}

	public EcuEntity fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       EcuEntity ecu = new EcuEntity();
       
       URL resource = classLoader.getResource("data/ecu/" + type  + ".json");
        
       try {
    	   ecu = objectMapper.readValue(new File(resource.getFile()), EcuEntity.class);
		
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
