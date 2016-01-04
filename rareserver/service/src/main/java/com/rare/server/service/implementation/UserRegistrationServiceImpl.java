package com.rare.server.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rare.server.persistence.UserDao;
import com.rare.server.persistence.exception.DaoException;
import com.rare.server.persistence.input.UserRegistrationDaoInput;
import com.rare.server.service.UserRegistrationService;
import com.rare.server.service.error.ServiceError;
import com.rare.server.service.exception.ServiceException;
import com.rare.server.service.global.ServiceConstants;
import com.rare.server.service.input.UserRegistrationServiceInput;
import com.rare.server.service.result.UserRegistrationResult;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	UserDao userDao;

	@Override
	public UserRegistrationResult registerUser(UserRegistrationServiceInput userInput) throws ServiceException {
		UserRegistrationDaoInput userRegistrationDaoInput = generateDaoInput(userInput);
		try {
			this.getUserDao().insertUser(userRegistrationDaoInput);
			return generateSuccessServiceResult();
		} catch (DaoException e) {
			throw new ServiceException(ServiceError.ERROR_IN_DATABASE, e);
		}
	}

	private UserRegistrationResult generateSuccessServiceResult() {
		UserRegistrationResult result = new UserRegistrationResult();
		result.setMessage(ServiceConstants.SUCCESSFULLY_REGISTERED_USER);
		return result;
	}

	private UserRegistrationDaoInput generateDaoInput(UserRegistrationServiceInput userInput) {
		UserRegistrationDaoInput userRegistrationDaoInput = new UserRegistrationDaoInput();
		BeanUtils.copyProperties(userInput, userRegistrationDaoInput);
		return userRegistrationDaoInput;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
