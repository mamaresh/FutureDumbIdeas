package com.rare.server.service.input;

import java.util.List;

public class UserRegistrationServiceInput {

	private String gender;

	private String displayName;

	private String url;

	private String objectType;

	private Integer totalItems;

	private String title;

	private String googleId;

	private List<UserFriendServiceInput> userFriendServiceInputs;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public List<UserFriendServiceInput> getUserFriendInputs() {
		return userFriendServiceInputs;
	}

	public void setUserFriendInputs(List<UserFriendServiceInput> userFriendServiceInputs) {
		this.userFriendServiceInputs = userFriendServiceInputs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((googleId == null) ? 0 : googleId.hashCode());
		result = prime * result + ((objectType == null) ? 0 : objectType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((totalItems == null) ? 0 : totalItems.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((userFriendServiceInputs == null) ? 0 : userFriendServiceInputs.hashCode());
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
		UserRegistrationServiceInput other = (UserRegistrationServiceInput) obj;
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
		if (googleId == null) {
			if (other.googleId != null)
				return false;
		} else if (!googleId.equals(other.googleId))
			return false;
		if (objectType == null) {
			if (other.objectType != null)
				return false;
		} else if (!objectType.equals(other.objectType))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (totalItems == null) {
			if (other.totalItems != null)
				return false;
		} else if (!totalItems.equals(other.totalItems))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (userFriendServiceInputs == null) {
			if (other.userFriendServiceInputs != null)
				return false;
		} else if (!userFriendServiceInputs.equals(other.userFriendServiceInputs))
			return false;
		return true;
	}

}
