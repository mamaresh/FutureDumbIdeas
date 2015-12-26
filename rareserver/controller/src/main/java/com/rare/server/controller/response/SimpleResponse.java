package com.rare.server.controller.response;

public class SimpleResponse {

	protected ResponseStatus status = ResponseStatus.OK;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleResponse other = (SimpleResponse) obj;
		if (status != other.status)
			return false;
		return true;
	}

}
