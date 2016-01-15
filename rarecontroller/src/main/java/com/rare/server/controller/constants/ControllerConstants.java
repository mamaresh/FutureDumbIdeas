package com.rare.server.controller.constants;

public class ControllerConstants {

	public static final String USER_REQUEST_MAPPING = "/user";
	public static final String REGISTER_USER_MAPPING = "/register";
	public static final String GET_USER = "/getUser/";
	public static final String USER_GOOGLE_ID_VARIABLE = "/{googleId}";
	public static final String CHARSET = ";charset=utf-8";
	public static final String SUCCESSFULLY_REGISTERED = "Successfully obtained user from google";
	public static final String COULD_NOT_OBTAIN_INFORMATION_FROM_GOOGLE = "Could not obtain user information from google";
	public static final String USER_DOES_NOT_EXIST = "User does not exist";

	private ControllerConstants() {

	}

}
