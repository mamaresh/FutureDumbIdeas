package com.rare.server.service.result;

import java.util.List;

public class UserGetFriendsResult {

	private String googleId;

	private List<String> friendNames;

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public List<String> getFriendNames() {
		return friendNames;
	}

	public void setFriendNames(List<String> friendNames) {
		this.friendNames = friendNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendNames == null) ? 0 : friendNames.hashCode());
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
		UserGetFriendsResult other = (UserGetFriendsResult) obj;
		if (friendNames == null) {
			if (other.friendNames != null)
				return false;
		} else if (!friendNames.equals(other.friendNames))
			return false;
		if (googleId == null) {
			if (other.googleId != null)
				return false;
		} else if (!googleId.equals(other.googleId))
			return false;
		return true;
	}

}
