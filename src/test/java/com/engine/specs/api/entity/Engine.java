package com.engine.specs.api.entity;

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
				"'valveAmount': " + valveAmount + "'" +
				"'injectors': '" + injectors + "'" +
				"'pistonType': '" + pistonType + "'" +
				"'camshaft': '" + camshaft + "'" +
				"'power': '" + power + "'" +
				"'forcedInduction': '" + forcedInduction + "'" +
				"'forcedInductionType': '" + forcedInductionType + "'" +
				"'forcedInductionModel': '" + forcedInductionModel + "'" +
				"'fuelType': '" + fuelType + "'" +
				"}";
	}

    public String getModel() {
	 	return model;
	}

	public int getDisplacement() {
		return displacement;
	}

	public int getValveAmount() {
		return valveAmount;
	}

	public String getInjectors() {
		return injectors;
	}


	public String getPistonType() {
		return pistonType;
	}

	public String getCamshaft() {
		return camshaft;
	}

	public int getPower() {
		return power;
	}
	
	public boolean isForcedInduction() {
		return forcedInduction;
	}

	public String getForcedInductionType() {
		return forcedInductionType;
	}

	public String getForcedInductionModel() {
		return forcedInductionModel;
	}

	public String getFuelType() {
		return fuelType;
	}
}
