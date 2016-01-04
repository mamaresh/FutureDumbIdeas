package com.rare.server.persistence.exception;

import com.rare.server.persistence.error.DaoError;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	protected DaoError daoError;

	public DaoException(DaoError daoError) {
		this.daoError = daoError;
	}

	public DaoException(DaoError daoError, Throwable cause) {
		super(cause);
		this.daoError = daoError;
	}

	public DaoError getControllerError() {
		return daoError;
	}

	public void setControllerError(DaoError daoError) {
		this.daoError = daoError;
	}
}
