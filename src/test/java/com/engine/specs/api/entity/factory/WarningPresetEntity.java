package com.engine.specs.api.entity.factory;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "warning_preset")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarningPresetEntity {
	private String name;
	private int ect_warning;
	private int oil_temp_warning;
	private int rpm_warning;
	private EngineEntity engine;
	
	@JsonGetter("name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonGetter("ect_warning")
	public int getEct_warning() {
		return ect_warning;
	}
	
	public void setEct_warning(int ect_warning) {
		this.ect_warning = ect_warning;
	}
	
	@JsonGetter("oil_temp_warning")
	public int getOil_temp_warning() {
		return oil_temp_warning;
	}
	
	public void setOil_temp_warning(int oil_temp_warning) {
		this.oil_temp_warning = oil_temp_warning;
	}
	
	@JsonGetter("rpm_warning")
	public int getRpm_warning() {
		return rpm_warning;
	}
	
	public void setRpm_warning(int rpm_warning) {
		this.rpm_warning = rpm_warning;
	}
	
	@JsonGetter("engine")
	public EngineEntity getEngine() {
		return engine;
	}
	
	public void setEngine(EngineEntity engine) {
		this.engine = engine;
	}
	
	@Override
	public String toString() {
		return "'warning_preset': {" +
				"'name': '" + name + "'" +
				"'ect_warning': '" + ect_warning + "'" +
				"'oil_temp_warning': " + oil_temp_warning + "'" +
				"'rpm_warning': '" + rpm_warning + "'" +
				"'engine': '" + engine + "'" +
				"}";
	}
}
