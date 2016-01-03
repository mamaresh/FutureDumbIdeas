package com.rare.server.service.input;

import java.util.List;

public class UserRegistration {
	
	private String gender;
	
	private String displayName;
	
	private String url;
	
	private String objectType;
	
	private Integer totalItems;
	
	private List<UserFriend> userFriends;

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

	public List<UserFriend> getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(List<UserFriend> userFriends) {
		this.userFriends = userFriends;
	}
	
}
