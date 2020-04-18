package com.engine.specs.api.entity.factory;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;

public class InjectionMapEntity {
	private Map<String, Float> map;
	private String date;
	private EcuEntity ecu;
	private String user;
	
	@JsonGetter("map")
	public Map<String, Float> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Float> map) {
		this.map = map;
	}
	
	@JsonGetter("date")
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
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
}
