package com.engine.specs.api.entity.builder;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Engine {
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
    
    public static class Builder {
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
        
        public Builder model (String model) {
        	this.model = model;
        	return this;
        }
        
        public Builder displacement(int displacement) {
        	this.displacement = displacement;
        	return this;
        }
        
        public Builder valveAmount(int valveAmount) {
        	this.valveAmount = valveAmount;
        	return this;
        }
        
        public Builder injectors (String injectors) {
        	this.injectors = injectors;
        	return this;
        }
        
        public Builder pistonType(String pistonType) {
        	this.pistonType = pistonType;
        	return this;
        }
        
        public Builder camshaft(String camshaft) {
        	this.camshaft = camshaft;
        	return this;
        }
        
        public Builder power (int power) {
        	this.power = power;
        	return this;
        }
        
        public Builder forcedInduction(boolean forcedInduction) {
        	this.forcedInduction = forcedInduction;
        	return this;
        }
        
        public Builder forcedInductionType(String forcedInductionType) {
        	this.forcedInductionType = forcedInductionType;
        	return this;
        }
        
        public Builder forcedInductionModel(String forcedInductionModel) {
        	this.forcedInductionModel = forcedInductionModel;
        	return this;
        }
        
        public Builder fuelType(String fuelType) {
        	this.fuelType = fuelType;
        	return this;
        }
        
        public Engine build() {
			return new Engine(this);
		}
    }
    
    private Engine(Builder builder) {
        model = builder.model;
        displacement = builder.displacement;
        valveAmount = builder.valveAmount;
        injectors = builder.injectors;
        pistonType = builder.pistonType;
        camshaft = builder.camshaft;
        power = builder.power;
        forcedInduction = builder.forcedInduction;
        forcedInductionType = builder.forcedInductionType;
        forcedInductionModel = builder.forcedInductionModel;
        fuelType = builder.fuelType;
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
