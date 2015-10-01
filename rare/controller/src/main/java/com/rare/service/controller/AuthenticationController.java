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

import com.rare.commons.constants.ControllerConstants;
import com.rare.commons.exception.LogicException;
import com.rare.commons.util.JSONParser;
import com.rare.logic.UserHandler;

@RestController
@RequestMapping(value = ControllerConstants.AUTHENTICATION_CONTROLLER_PATH)
public class AuthenticationController {

	@Autowired
	UserHandler userHandler;

	@RequestMapping(value = ControllerConstants.AUTHENTICATION_CONTROLLER_LOGIN_PATH, method = RequestMethod.POST)
	public ResponseEntity<String> checkLoginPossibleOrNot(@RequestBody String userCredentialsJson) {
		Map<String, Object> map;
		ResponseEntity<String> responseEntity;
		try {
			map = JSONParser.parseJson(new JSONObject(userCredentialsJson));
			String userName = (String) map.get(ControllerConstants.USERNAME);
			String password = (String) map.get(ControllerConstants.PASSWORD);
			boolean check = this.getUserHandler().checkCredentials(userName, password);
			if (check) {
				responseEntity = new ResponseEntity<String>(ControllerConstants.SUCCESSFULLY_LOGGED_IN, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<String>(ControllerConstants.WRONG_CREDENTIALS,
						HttpStatus.UNAUTHORIZED);
			}
		} catch (JSONException e) {
			responseEntity = new ResponseEntity<String>(ControllerConstants.ERROR_IN_INPUT, HttpStatus.BAD_REQUEST);
		} catch (LogicException e) {
			responseEntity = new ResponseEntity<String>(ControllerConstants.INTERNAL_SERVER_ERROR,
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
