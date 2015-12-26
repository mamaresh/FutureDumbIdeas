package com.rare.server.apicaller.error;

public enum ApiCallerError {

	//@formatter:off
	NO_ERROR				("", ""), 
	GOOGLE_API_ERROR		("GAPI001", "Google API returned response code which is not acceptable"), 
	GOOGLE_API_URL_ERROR	("GAPI002", "Google API URL error"), 
	GOOGLE_API_IO_EXCEPTION	("GAPI003","Unable to read the data returned from Google API");

	// @formatter:on
	private final String code;
	private final String description;

	private ApiCallerError(String code, String description) {
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
