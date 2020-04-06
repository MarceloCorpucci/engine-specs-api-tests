package com.engine.specs.api.entity.factory;

import com.fasterxml.jackson.annotation.JsonGetter;

public class EcuEntity {
	private String model;
	private String firmware;
	private String dateAdded;
	private EngineEntity engine;
	private WarningPresetEntity warningPreset;
	private User user;
	
	@JsonGetter("model")
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@JsonGetter("firmware")
	public String getFirmware() {
		return firmware;
	}
	
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	
	@JsonGetter("date_added")
	public String getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	@JsonGetter("engine")
	public EngineEntity getEngine() {
		return engine;
	}
	
	public void setEngine(EngineEntity engine) {
		this.engine = engine;
	}
	
	@JsonGetter("warning_preset")
	public WarningPresetEntity getWarningPreset() {
		return warningPreset;
	}
	
	
	public void setWarningPreset(WarningPresetEntity warningPreset) {
		this.warningPreset = warningPreset;
	}
	
	@JsonGetter("user")
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
}
