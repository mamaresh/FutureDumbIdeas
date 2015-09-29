package com.rare.commons.exception;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = null;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String exception) {
		super(exception);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

	public DatabaseException(String exception, Throwable cause) {
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
