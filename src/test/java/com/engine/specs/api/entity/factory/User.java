package com.engine.specs.api.entity.factory;

import com.fasterxml.jackson.annotation.JsonGetter;

public class User {
	private String email;
	private String password;

	@JsonGetter("email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonGetter("password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
