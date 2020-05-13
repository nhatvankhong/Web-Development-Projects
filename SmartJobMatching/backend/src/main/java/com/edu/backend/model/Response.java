package com.edu.backend.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response implements Serializable {

	private static final long serialVersionUID = 1412601354352798261L;
	
	protected long userId;
	
	protected String message;

	public Response() {
		super();
	}
	
	public Response(String message) {
		super();
		this.message = message;
	}
	
	public Response(String message, long userId) {
		super();
		this.message = message;
		this.userId = userId;
	}
}