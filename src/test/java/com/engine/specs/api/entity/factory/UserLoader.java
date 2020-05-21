package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.engine.specs.api.entity.UserEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserLoader implements DomainEntityLoader<UserEntity> {
	private String type;
	
	@Override
	public DomainEntityLoader<UserEntity> get(String type) {
		this.type = type;
		return this;
	}

	@Override
	public UserEntity fromJsonResource() {
       ClassLoader classLoader = getClass().getClassLoader();
       ObjectMapper objectMapper = new ObjectMapper();
       UserEntity user = new UserEntity();
       
       URL resource = classLoader.getResource("data/user/" + type  + ".json");
        
       try {
    	   user = objectMapper.readValue(new File(resource.getFile()), UserEntity.class);
		
       } catch (JsonParseException e) {
    	   e.printStackTrace();
		
       } catch (JsonMappingException e) {
    	   e.printStackTrace();
		
       } catch (IOException e) {
    	   e.printStackTrace();
       }
        
       return user;
	}

}
