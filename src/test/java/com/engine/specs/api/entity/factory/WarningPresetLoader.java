package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.engine.specs.api.entity.WarningPresetEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WarningPresetLoader  implements DomainEntityLoader<WarningPresetEntity> {
	private String type;
	
	public DomainEntityLoader<WarningPresetEntity> get(String type) {
		this.type = type;
		return this;
	}

	public WarningPresetEntity fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       WarningPresetEntity warn_preset = new WarningPresetEntity();
       
       URL resource = classLoader.getResource("data/warning_preset/" + type  + ".json");
        
       try {
    	   warn_preset = objectMapper.readValue(new File(resource.getFile()), WarningPresetEntity.class);
		
       } catch (JsonParseException e) {
    	   e.printStackTrace();
		
       } catch (JsonMappingException e) {
    	   e.printStackTrace();
		
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       
       return warn_preset;
	}
}
