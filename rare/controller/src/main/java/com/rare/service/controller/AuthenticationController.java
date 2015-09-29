package com.rare.service.controller;

import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rare.commons.exception.LogicException;
import com.rare.commons.util.JSONParser;
import com.rare.logic.UserHandler;

@RestController
@RequestMapping(value = "/user")
public class AuthenticationController {

	@Autowired
	UserHandler userHandler;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> checkLoginPossibleOrNot(
			@RequestBody String userCredentialsJson) {
		Map<String, Object> map;
		String responseString = null;
		ResponseEntity<String> responseEntity;
		try {
			map = JSONParser.parseJson(new JSONObject(userCredentialsJson));
			String userName = (String) map.get("userName");
			String password = (String) map.get("password");
			boolean check = this.getUserHandler().checkCredentials(userName,
					password);
			if (check) {
				responseString = "Successfully logged in";
				responseEntity = new ResponseEntity<String>(responseString,
						HttpStatus.OK);
			} else {
				responseString = "Login failed, user credentials wrong";
				responseEntity = new ResponseEntity<String>(responseString,
						HttpStatus.UNAUTHORIZED);
			}
		} catch (JSONException e) {
			responseString = "Error in json";
			responseEntity = new ResponseEntity<String>(responseString,
					HttpStatus.BAD_REQUEST);
		} catch (LogicException e) {
			responseString = "Error in server";
			responseEntity = new ResponseEntity<String>(responseString,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	public UserHandler getUserHandler() {
		return userHandler;
	}

	public void setUserHandler(UserHandler userHandler) {
		this.userHandler = userHandler;
	}
}
