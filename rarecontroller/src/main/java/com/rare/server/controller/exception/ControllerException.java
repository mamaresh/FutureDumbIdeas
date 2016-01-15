package com.rare.server.controller.exception;

import com.rare.server.controller.error.ControllerError;

public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	protected ControllerError controllerError;

	public ControllerException(ControllerError controllerError) {
		this.controllerError = controllerError;
	}

	public ControllerException(ControllerError controllerError, Throwable cause) {
		super(cause);
		this.controllerError = controllerError;
	}

	public ControllerError getControllerError() {
		return controllerError;
	}

	public void setControllerError(ControllerError controllerError) {
		this.controllerError = controllerError;
	}

}
