package com.engine.specs.api.entity;

public class Engine {
	private String model;
    private int displacement;
    private int valve_amount;
    private String injectors;
    private String piston_type;
    private String camshaft;
    private int power;
    private boolean forced_induction;
    private String forced_induction_type;
    private String forced_induction_model;
    private String fuel_type;
    
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
        valve_amount = builder.valveAmount;
        injectors = builder.injectors;
        piston_type = builder.pistonType;
        camshaft = builder.camshaft;
        power = builder.power;
        forced_induction = builder.forcedInduction;
        forced_induction_type = builder.forcedInductionType;
        forced_induction_model = builder.forcedInductionModel;
        fuel_type = builder.fuelType;
    }
    
	@Override
	public String toString() {
		return "Engine {" +
				"'model': '" + model + "'" +
				"'displacement': '" + displacement + "'" +
				"'valve_amount': " + valve_amount + "'" +
				"'injectors': '" + injectors + "'" +
				"'piston_type': '" + piston_type + "'" +
				"'camshaft': '" + camshaft + "'" +
				"'power': '" + power + "'" +
				"'forced_induction': '" + forced_induction + "'" +
				"'forced_inductionType': '" + forced_induction_type + "'" +
				"'forced_inductionModel': '" + forced_induction_model + "'" +
				"'fuel_type': '" + fuel_type + "'" +
				"}";
	}

    public String getmodel() {
	 	return model;
	}

	public int getdisplacement() {
		return displacement;
	}

	public int getvalve_amount() {
		return valve_amount;
	}

	public String getinjectors() {
		return injectors;
	}


	public String getpiston_type() {
		return piston_type;
	}

	public String getcamshaft() {
		return camshaft;
	}

	public int getpower() {
		return power;
	}
	
	public boolean isforced_induction() {
		return forced_induction;
	}

	public String getforced_induction_type() {
		return forced_induction_type;
	}

	public String getforced_induction_model() {
		return forced_induction_model;
	}

	public String getfuel_type() {
		return fuel_type;
	}
}
