package com.rare.server.service;

import com.rare.server.service.exception.ServiceException;
import com.rare.server.service.input.UserDeletionInput;
import com.rare.server.service.input.UserGetFriendsInput;
import com.rare.server.service.input.UserRegistrationServiceInput;
import com.rare.server.service.result.UserDeletionResult;
import com.rare.server.service.result.UserGetFriendsResult;
import com.rare.server.service.result.UserRegistrationResult;

public interface UserRegistrationService {

	public UserRegistrationResult registerUser(UserRegistrationServiceInput userInput) throws ServiceException;

	public UserGetFriendsResult getFriendsOfUser(UserGetFriendsInput userGetFriendsInput) throws ServiceException;
	
	public UserDeletionResult deleteUser(UserDeletionInput userInput) throws ServiceException;

}
