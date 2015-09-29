package com.rare.commons.exception;

public class LogicException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = null;

	public LogicException() {
		super();
	}

	public LogicException(String exception) {
		super(exception);
	}

	public LogicException(Throwable cause) {
		super(cause);
	}

	public LogicException(String exception, Throwable cause) {
		super(exception, cause);
	}

	@Override
	public String toString() {
		return MESSAGE;
	}

	@Override
	public String getMessage() {
		return MESSAGE;
	}

}
