package com.rare.server.persistence.input;

import java.util.List;

public class UserRegistrationDaoInput {

	private String gender;

	private String displayName;

	private String url;

	private String objectType;

	private Integer totalItems;

	private String title;

	private List<UserFriendDaoInput> userFriendDaoInputs;
	
	private String googleId;

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

	public List<UserFriendDaoInput> getUserFriendDaoInputs() {
		return userFriendDaoInputs;
	}

	public void setUserFriendDaoInputs(List<UserFriendDaoInput> userFriendDaoInputs) {
		this.userFriendDaoInputs = userFriendDaoInputs;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

}
