package com.rare.server.controller.response;

import java.util.ArrayList;
import java.util.List;

import com.rare.server.controller.error.ControllerError;
import com.rare.server.controller.error.Error;

public class ErrorResponse extends SimpleResponse {

	private List<Error> errors;

	public ErrorResponse() {
		super();
		this.status = ResponseStatus.ERROR;
	}

	public ErrorResponse(ControllerError errInfo) {

		this.status = ResponseStatus.ERROR;

		List<Error> el = new ArrayList<Error>();
		Error ei = new Error();
		ei.setErrorCode(errInfo.getCode());
		ei.setMessage(errInfo.getDescription());
		el.add(ei);
		errors = el;
	}

	public ErrorResponse(String code, String message) {

		this.status = ResponseStatus.ERROR;

		List<Error> el = new ArrayList<Error>();
		Error ei = new Error();
		ei.setErrorCode(code);
		ei.setMessage(message);
		el.add(ei);
		errors = el;
	}

	public ErrorResponse(List<Error> errors) {
		super();
		this.errors = errors;
		this.status = ResponseStatus.ERROR;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ErrorResponse other = (ErrorResponse) obj;
		if (errors == null) {
			if (other.errors != null) {
				return false;
			}
		} else if (errors.size() != other.errors.size() || !(errors.containsAll(other.errors))) {
			return false;
		}
		return true;
	}

}
