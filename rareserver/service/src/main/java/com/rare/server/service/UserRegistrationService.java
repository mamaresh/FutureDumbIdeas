package com.rare.server.service;

import com.rare.server.service.exception.ServiceException;
import com.rare.server.service.input.UserRegistrationServiceInput;
import com.rare.server.service.result.UserRegistrationResult;

public interface UserRegistrationService {

	public UserRegistrationResult registerUser(UserRegistrationServiceInput userInput)
			throws ServiceException;

}
