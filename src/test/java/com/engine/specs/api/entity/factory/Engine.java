package com.engine.specs.api.entity.factory;

public interface Engine {
	public String getmodel();
	public int getdisplacement();
	public int getvalve_amount();
	public String getinjectors();
	public String getpiston_type();
	public String getcamshaft();
	public int getpower();
	public boolean isforced_induction();
	public String getforced_induction_type();
	public String getforced_induction_model();
	public String getfuel_type();
}
