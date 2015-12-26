package com.rare.server.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UserRegisterRequest {

	@NotNull
	private Long mobileNumber;

	@NotNull
	@NotBlank
	private String googleId;

	private String facebookId;

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

}
