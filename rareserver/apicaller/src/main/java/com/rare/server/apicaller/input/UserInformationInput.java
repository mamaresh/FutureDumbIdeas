package com.rare.server.apicaller.input;

public class UserInformationInput {

	private String googleId;

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((googleId == null) ? 0 : googleId.hashCode());
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
		UserInformationInput other = (UserInformationInput) obj;
		if (googleId == null) {
			if (other.googleId != null)
				return false;
		} else if (!googleId.equals(other.googleId))
			return false;
		return true;
	}

}
