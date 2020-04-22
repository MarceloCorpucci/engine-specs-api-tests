package com.engine.specs.api.entity.factory;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "engine")
public class EngineEntity {
	private String model;
	private int displacement;
	private int valveAmount;
	private String injectors;
	private String pistonType;
	private String camshaft;
	private int power;
	private boolean forcedInduction;
	private String forcedInductionType;
	private String forcedInductionModel;
	private String fuelType;
	
	@JsonGetter("model")
	public String getModel() {
		return model;
	}
	
	@JsonGetter("displacement")
	public int getDisplacement() {
		return displacement;
	}
	
	@JsonGetter("valve_amount")
	public int getValveAmount() {
		return valveAmount;
	}
	
	@JsonGetter("injectors")
	public String getInjectors() {
		return injectors;
	}
	
	@JsonGetter("piston_type")
	public String getPistonType() {
		return pistonType;
	}
	
	@JsonGetter("camshaft")
	public String getCamshaft() {
		return camshaft;
	}
	
	@JsonGetter("power")
	public int getPower() {
		return power;
	}
	
	@JsonGetter("forced_induction")
	public boolean isForcedInduction() {
		return forcedInduction;
	}
	
	@JsonGetter("forced_induction_type")
	public String getForcedInductionType() {
		return forcedInductionType;
	}
	
	@JsonGetter("forced_induction_model")
	public String getForcedInductionModel() {
		return forcedInductionModel;
	}
	
	@JsonGetter("fuel_type")
	public String getFuelType() {
		return fuelType;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}
	
	public void setValveAmount(int valveAmount) {
		this.valveAmount = valveAmount;
	}
	
	public void setInjectors(String injectors) {
		this.injectors = injectors;
	}
	
	public void setPistonType(String pistonType) {
		this.pistonType = pistonType;
	}
	
	public void setCamshaft(String camshaft) {
		this.camshaft = camshaft;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public void setForcedInduction(boolean forcedInduction) {
		this.forcedInduction = forcedInduction;
	}
	
	public void setForcedInductionType(String forcedInductionType) {
		this.forcedInductionType = forcedInductionType;
	}
	
	public void setForcedInductionModel(String forcedInductionModel) {
		this.forcedInductionModel = forcedInductionModel;
	}
	
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "'engine': {" +
				"'model': '" + model + "'" +
				"'displacement': '" + displacement + "'" +
				"'valve_amount': " + valveAmount + "'" +
				"'injectors': '" + injectors + "'" +
				"'piston_type': '" + pistonType + "'" +
				"'camshaft': '" + camshaft + "'" +
				"'power': '" + power + "'" +
				"'forced_induction': '" + forcedInduction + "'" +
				"'forced_inductionType': '" + forcedInductionType + "'" +
				"'forced_inductionModel': '" + forcedInductionModel + "'" +
				"'fuel_type': '" + fuelType + "'" +
				"}";
	}
}
