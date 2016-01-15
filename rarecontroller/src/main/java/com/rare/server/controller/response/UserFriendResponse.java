package com.rare.server.controller.response;

import java.util.List;

public class UserFriendResponse extends UserCommonResponse {
	
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

}
