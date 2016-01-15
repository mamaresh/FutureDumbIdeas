package com.rare.server.service.exception;

import com.rare.server.service.error.ServiceError;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	protected ServiceError serviceError;

	public ServiceException(ServiceError serviceError) {
		this.serviceError = serviceError;
	}

	public ServiceException(ServiceError serviceError, Throwable cause) {
		super(cause);
		this.serviceError = serviceError;
	}

	public ServiceError getControllerError() {
		return serviceError;
	}

	public void setControllerError(ServiceError serviceError) {
		this.serviceError = serviceError;
	}
}
