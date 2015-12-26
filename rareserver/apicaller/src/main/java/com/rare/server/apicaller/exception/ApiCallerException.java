package com.rare.server.apicaller.exception;

import com.rare.server.apicaller.error.ApiCallerError;

public class ApiCallerException extends Exception {

	private static final long serialVersionUID = 1L;

	protected ApiCallerError error;

	public ApiCallerException(ApiCallerError error) {
		this.error = error;
	}

	public ApiCallerException(ApiCallerError error, Throwable cause) {
		super(cause);
		this.error = error;
	}

	public ApiCallerError getError() {
		return error;
	}

	public void setError(ApiCallerError error) {
		this.error = error;
	}

}
