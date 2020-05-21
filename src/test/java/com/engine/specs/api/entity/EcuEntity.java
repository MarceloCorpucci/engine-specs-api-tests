package com.engine.specs.api.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ecu")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcuEntity {
	private String model;
	private String firmware;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Date dateAdded;
	private EngineEntity engine;
	private WarningPresetEntity warningPreset;
	private UserEntity user;
	
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	public Date getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@JsonGetter("model")
	public String getModel() {
		return model;
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
	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "'ecu': {" +
				"'model': '" + model + "'" +
				"'firmware': '" + firmware + "'" +
				"'date_added': '" + dateAdded + "'" +
				"'engine': '" + engine + "'" +
				"'warning_preset': '" + warningPreset + "'" +
				"'user': '" + user + "'" +
				"}";
	}
}
