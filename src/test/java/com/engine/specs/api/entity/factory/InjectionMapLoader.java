package com.engine.specs.api.entity.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.engine.specs.api.entity.InjectionMapEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
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
       InjectionMapEntity injectionMap = new InjectionMapEntity(); //new HashMap<String, Float>());
       Map<String, InjectionMapEntity> result;
       
//       TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, Float>>() {};
//       Map<String, Float> map = mapper.readValue(jsonInput, typeRef);
//       
//       TypeFactory typeFactory = mapper.getTypeFactory();
//       MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Theme.class);
//       HashMap<String, Theme> map = mapper.readValue(json, mapType);
       
       URL resource = classLoader.getResource("data/injection_map/" + type  + ".json");
        
       try {
    	   result = objectMapper.readValue(new File(resource.getFile()), new TypeReference<Map<String,InjectionMapEntity>>() {}); // InjectionMapEntity.class);
		
       } catch (JsonParseException e) {
    	   e.printStackTrace();
		
       } catch (JsonMappingException e) {
    	   e.printStackTrace();
		
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       
       //return injectionMap;
       return null;
	}

}
