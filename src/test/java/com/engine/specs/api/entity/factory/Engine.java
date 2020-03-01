package com.engine.specs.api.entity.factory;

public interface Engine {
	public String getModel();
	public int getDisplacement();
	public int getValveAmount();
	public String getInjectors();
	public String getPistonType();
	public String getCamshaft();
	public int getPower();
	public boolean isForcedInduction();
	public String getForcedInductionType();
	public String getForcedInductionModel();
	public String getFuelType();
}
