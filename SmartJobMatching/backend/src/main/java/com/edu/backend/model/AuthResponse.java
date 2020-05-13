package com.edu.backend.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthResponse extends Response {

	private static final long serialVersionUID = 2319497642843948222L;

	protected boolean hasProfile;
	
	public AuthResponse() {
		super();
	}	
	
	public AuthResponse(long userId, boolean hasProfile, String message) {
		super();
		this.userId = userId;
		this.hasProfile = hasProfile;
		this.message = message;
	}
}