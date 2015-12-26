package com.rare.server.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rare.server.apicaller.UserInformationCaller;
import com.rare.server.apicaller.exception.ApiCallerException;
import com.rare.server.apicaller.input.UserInformationInput;
import com.rare.server.apicaller.result.UserInformationResult;
import com.rare.server.controller.constants.Constants;
import com.rare.server.controller.exception.ControllerException;
import com.rare.server.controller.request.UserRegisterRequest;
import com.rare.server.controller.response.UserRegisterResponse;

@RestController
@RequestMapping(value = Constants.USER_REQUEST_MAPPING)
public class UserController extends BaseController {

	@Autowired
	UserInformationCaller caller;

	@RequestMapping(value = Constants.REGISTER_USER_MAPPING, method = {
			RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
					+ Constants.CHARSET)
	public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request)
			throws ControllerException, ApiCallerException {
		UserInformationResult result = this.getCaller()
				.callGoogleApiToGetPersonalInformation(generateUserInformationInput(request));
		if (result != null) {
			return new ResponseEntity<UserRegisterResponse>(
					new UserRegisterResponse("Successfully obtained user from google"), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRegisterResponse>(
					new UserRegisterResponse("Could not obtain user information"), HttpStatus.FORBIDDEN);
		}
	}

	private UserInformationInput generateUserInformationInput(UserRegisterRequest request) {
		UserInformationInput input = new UserInformationInput();
		BeanUtils.copyProperties(request, input);
		return input;
	}

	public UserInformationCaller getCaller() {
		return caller;
	}

	public void setCaller(UserInformationCaller caller) {
		this.caller = caller;
	}

}
