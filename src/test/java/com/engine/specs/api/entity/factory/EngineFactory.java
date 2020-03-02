package com.engine.specs.api.entity.factory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EngineFactory {
	private EngineMinRepresentation engineMinRepresentation;
	Map<String, Object> map;
	private Map<String, Object> elem;
	
	public void getJson() {
		try {
			String path = "src/test/resources/engines.json";
			map = new ObjectMapper()
							.readValue(new URL("file:" + path), 
									   new TypeReference<Map<String,Object>>(){});
			
			ArrayList<?> array = (ArrayList<?>) map.get("engines");
			Map<String, Object> item = (Map<String, Object>) array.get(0);
			elem = (Map<String, Object>) item.get("min");
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Engine getEngine(String reprType) {
		if (reprType == "min") {
			return engineMinRepresentation;
		}
//		
//		if (reprType == "full") {
//			return new EngineFullRepresentation();
//		}
		
		return null;
	}

}
