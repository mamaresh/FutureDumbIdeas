package com.rare.server.persistence.error;

public enum DaoError {

	// @formatter:off
	UNDEFINED_ERROR		("", ""),
    DRIVER_EXCEPTION 	("DAO001", "Error in driver");
	
	// @formatter:on
	private final String code;
	private final String description;

	private DaoError(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}

}
