package com.rare.server.apicaller.result;

public class UserInformationResult {
	
	private String gender;
	
	private String displayName;
	
	private String googlePlusUrl;
	
	private String objectType;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getGooglePlusUrl() {
		return googlePlusUrl;
	}

	public void setGooglePlusUrl(String googlePlusUrl) {
		this.googlePlusUrl = googlePlusUrl;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((googlePlusUrl == null) ? 0 : googlePlusUrl.hashCode());
		result = prime * result + ((objectType == null) ? 0 : objectType.hashCode());
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
		UserInformationResult other = (UserInformationResult) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (googlePlusUrl == null) {
			if (other.googlePlusUrl != null)
				return false;
		} else if (!googlePlusUrl.equals(other.googlePlusUrl))
			return false;
		if (objectType == null) {
			if (other.objectType != null)
				return false;
		} else if (!objectType.equals(other.objectType))
			return false;
		return true;
	}

}
