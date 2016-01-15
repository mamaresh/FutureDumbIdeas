package com.rare.server.controller.response;

public class UserNotFoundResponse extends UserCommonResponse {

	private String message;
	
	public UserNotFoundResponse(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
