package com.rare.server.service.error;

public enum ServiceError {

	// @formatter:off
	NO_ERROR			("", ""), 
	UNDEFINED_ERROR		("SER001", "An undefined error occurred"), 
	ERROR_IN_DATABASE	("SER002", "Unable to parse json"),;

	// @formatter:on
	private final String code;
	private final String description;

	private ServiceError(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
