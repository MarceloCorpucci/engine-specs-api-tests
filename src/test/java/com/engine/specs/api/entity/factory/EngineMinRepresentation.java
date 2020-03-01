package com.engine.specs.api.entity.factory;

import com.fasterxml.jackson.annotation.JsonGetter;

public class EngineMinRepresentation implements Engine {
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
    
	public EngineMinRepresentation(String model,
								   int displacement,
								   int valveAmount,
								   String injectors,
								   String pistonType,
								   String camshaft,
								   int power,
								   boolean forcedInduction,
								   String forcedInductionType,
								   String forcedInductionModel,
								   String fuelType) {
		this.model = model;
		this.displacement = displacement;
		this.valveAmount = valveAmount;
		this.injectors = injectors;
		this.pistonType = pistonType;
		this.camshaft = camshaft;
		this.power = power;
		this.forcedInduction = forcedInduction;
		this.forcedInductionType = forcedInductionType;
		this.forcedInductionModel = forcedInductionModel;
		this.fuelType = fuelType;	
	}
	
	@Override
	public String toString() {
		return "Engine {" +
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
}
