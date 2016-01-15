package com.rare.server.controller.error;

public enum ControllerError {

	// @formatter:off
	NO_ERROR			("", ""), 
	UNDEFINED_ERROR		("CON001", "An undefined error occurred"), 
	JSON_PARSE_ERROR	("CON002", "Unable to parse json"),
	ERROR_IN_API_CALLER ("CON003", "Error in API Caller");

	// @formatter:on
	private final String code;
	private final String description;

	private ControllerError(String code, String description) {
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
