package com.engine.specs.api.entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class InjectionMapEntity {
//	@JsonDeserialize(keyUsing = MyPairDeserializer.class)
//	@JsonProperty("map")
	private Map<String, Float> map;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private String date;
	private EcuEntity ecu;
	private User user;
	
//	@JsonCreator(mode=Mode.DELEGATING)
//	public InjectionMapEntity(@JsonProperty("map")Map<String,Float> map) {
//	    this.map = map;
//	}
//
//	@JsonValue
	@JsonGetter("map")
	public Map<String, Float> getMap() {
	    return map;
	}
	  
//	public Map<String, Float> getMap() {
//		return map;
//	}
	
	public void setMap(Map<String, Float> map) {
		this.map = map;
	}
	
	@JsonGetter("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	@JsonGetter("ecu")
	public EcuEntity getEcu() {
		return ecu;
	}
	
	public void setEcu(EcuEntity ecu) {
		this.ecu = ecu;
	}
	
	@JsonGetter("email")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
