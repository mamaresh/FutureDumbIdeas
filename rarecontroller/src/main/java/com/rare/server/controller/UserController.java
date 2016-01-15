package com.rare.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rare.server.apicaller.UserInformationCaller;
import com.rare.server.apicaller.exception.ApiCallerException;
import com.rare.server.apicaller.input.UserInformationInput;
import com.rare.server.apicaller.result.UserFriendResult;
import com.rare.server.apicaller.result.UserInformationResult;
import com.rare.server.controller.constants.ControllerConstants;
import com.rare.server.controller.exception.ControllerException;
import com.rare.server.controller.request.UserRegisterRequest;
import com.rare.server.controller.response.UserCommonResponse;
import com.rare.server.controller.response.UserFriendResponse;
import com.rare.server.controller.response.UserNotFoundResponse;
import com.rare.server.controller.response.UserRegisterResponse;
import com.rare.server.service.UserRegistrationService;
import com.rare.server.service.exception.ServiceException;
import com.rare.server.service.input.UserFriendServiceInput;
import com.rare.server.service.input.UserGetFriendsInput;
import com.rare.server.service.input.UserRegistrationServiceInput;
import com.rare.server.service.result.UserGetFriendsResult;

@RestController
@RequestMapping(value = ControllerConstants.USER_REQUEST_MAPPING)
public class UserController extends BaseController {

	@Autowired
	UserInformationCaller caller;

	@Autowired
	UserRegistrationService service;

	@RequestMapping(value = ControllerConstants.REGISTER_USER_MAPPING, method = {
			RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
					+ ControllerConstants.CHARSET)
	public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request)
			throws ControllerException, ApiCallerException, ServiceException {
		UserInformationResult result = this.getCaller()
				.callGoogleApiToGetPersonalInformation(generateUserInformationInput(request));
		UserFriendResult friendResult = this.getCaller().getFriendsInformation(generateUserInformationInput(request));
		if ((result != null) && (friendResult != null)) {
			UserRegistrationServiceInput input = generateUserRegistrationInput(result, friendResult);
			this.getService().registerUser(input);
			return new ResponseEntity<UserRegisterResponse>(
					new UserRegisterResponse(ControllerConstants.SUCCESSFULLY_REGISTERED), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRegisterResponse>(
					new UserRegisterResponse(ControllerConstants.COULD_NOT_OBTAIN_INFORMATION_FROM_GOOGLE),
					HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = ControllerConstants.GET_USER + ControllerConstants.USER_GOOGLE_ID_VARIABLE, method = {
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE + ControllerConstants.CHARSET)
	public ResponseEntity<UserCommonResponse> getUser(@PathVariable final String googleId)
			throws ControllerException, ServiceException {
		UserGetFriendsInput input = generateUserGetFriendInput(googleId);
		UserGetFriendsResult result = this.getService().getFriendsOfUser(input);
		if (result != null) {
			UserFriendResponse friendsResponse = generateUserFriendResponse(result);
			return new ResponseEntity<UserCommonResponse>(friendsResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserCommonResponse>(
					new UserNotFoundResponse(ControllerConstants.USER_DOES_NOT_EXIST), HttpStatus.OK);
		}
	}

	private UserFriendResponse generateUserFriendResponse(UserGetFriendsResult result) {
		UserFriendResponse response = new UserFriendResponse();
		BeanUtils.copyProperties(result, response);
		return response;
	}

	private UserGetFriendsInput generateUserGetFriendInput(String googleId) {
		UserGetFriendsInput input = new UserGetFriendsInput();
		input.setGoogleId(googleId);
		return input;
	}

	private UserRegistrationServiceInput generateUserRegistrationInput(UserInformationResult userInformation,
			UserFriendResult friends) {
		UserRegistrationServiceInput userRegistrationServiceInput = new UserRegistrationServiceInput();
		BeanUtils.copyProperties(userInformation, userRegistrationServiceInput);
		List<UserFriendServiceInput> userFriends = new ArrayList<>();
		for (int i = 0; i < friends.totalItems; i++) {
			UserFriendServiceInput userFriendServiceInput = new UserFriendServiceInput();
			BeanUtils.copyProperties(friends.getItems().get(i), userFriendServiceInput);
			userFriends.add(userFriendServiceInput);
		}
		userRegistrationServiceInput.setTotalItems(friends.totalItems);
		userRegistrationServiceInput.setUrl(userInformation.getUrl());
		userRegistrationServiceInput.setUserFriendInputs(userFriends);
		return userRegistrationServiceInput;
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

	public UserRegistrationService getService() {
		return service;
	}

	public void setService(UserRegistrationService service) {
		this.service = service;
	}

}
