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
import com.rare.server.apicaller.result.UserFriendResult;
import com.rare.server.apicaller.result.UserInformationResult;
import com.rare.server.controller.constants.Constants;
import com.rare.server.controller.exception.ControllerException;
import com.rare.server.controller.request.UserRegisterRequest;
import com.rare.server.controller.response.UserRegisterResponse;
import com.rare.server.service.UserRegistrationService;
import com.rare.server.service.exception.ServiceException;
import com.rare.server.service.input.UserRegistrationServiceInput;

@RestController
@RequestMapping(value = Constants.USER_REQUEST_MAPPING)
public class UserController extends BaseController {

	@Autowired
	UserInformationCaller caller;

	@Autowired
	UserRegistrationService service;

	@RequestMapping(value = Constants.REGISTER_USER_MAPPING, method = {
			RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
					+ Constants.CHARSET)
	public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request)
			throws ControllerException, ApiCallerException, ServiceException {
		UserInformationResult result = this.getCaller()
				.callGoogleApiToGetPersonalInformation(generateUserInformationInput(request));
		UserFriendResult friendResult = this.getCaller().getFriendsInformation(generateUserInformationInput(request));
		if ((result != null) && (friendResult != null)) {
			UserRegistrationServiceInput input = generateUserRegistrationInput(result, friendResult);
			this.getService().registerUser(input);
			return new ResponseEntity<UserRegisterResponse>(new UserRegisterResponse(Constants.SUCCESSFULLY_REGISTERED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRegisterResponse>(
					new UserRegisterResponse(Constants.COULD_NOT_OBTAIN_INFORMATION_FROM_GOOGLE), HttpStatus.FORBIDDEN);
		}
	}

	private UserRegistrationServiceInput generateUserRegistrationInput(UserInformationResult userInformation,
			UserFriendResult friends) {
		UserRegistrationServiceInput userRegistrationServiceInput = new UserRegistrationServiceInput();
		BeanUtils.copyProperties(userInformation, userRegistrationServiceInput);
		BeanUtils.copyProperties(friends, userRegistrationServiceInput);
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
