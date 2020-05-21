package com.engine.specs.api.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InjectionMapEntity {
	private List<Map<String, List<Float>>> map;
	private Date date;
	private EcuEntity ecu;
	private UserEntity user;
	
	@JsonCreator
	public InjectionMapEntity(@JsonProperty("map")
							  List<Map<String,List<Float>>> map,
							  @JsonProperty("date")
							  @JsonFormat(shape = JsonFormat.Shape.STRING, 
							  			  pattern = "yyyy-MM-dd HH:mm:ss", 
							  			  timezone = "GMT")
							  Date date,
							  @JsonProperty("ecu")
							  EcuEntity ecu,
							  @JsonProperty("user")
							  UserEntity user) {
	    this.map = map;
	    this.date = date;
	    this.ecu = ecu;
	    this.user = user;
	}

	public List<Map<String, List<Float>>> getMap() {
	    return map;
	}
	
	public void setMap(List<Map<String, List<Float>>> map) {
		this.map = map;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public EcuEntity getEcu() {
		return ecu;
	}
	
	public void setEcu(EcuEntity ecu) {
		this.ecu = ecu;
	}
	
	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}
}
