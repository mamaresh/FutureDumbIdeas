package com.rare.server.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rare.server.persistence.UserDao;
import com.rare.server.persistence.exception.DaoException;
import com.rare.server.persistence.input.UserFriendDaoInput;
import com.rare.server.persistence.input.UserRegistrationDaoInput;
import com.rare.server.service.UserRegistrationService;
import com.rare.server.service.error.ServiceError;
import com.rare.server.service.exception.ServiceException;
import com.rare.server.service.global.ServiceConstants;
import com.rare.server.service.input.UserDeletionInput;
import com.rare.server.service.input.UserGetFriendsInput;
import com.rare.server.service.input.UserRegistrationServiceInput;
import com.rare.server.service.result.UserDeletionResult;
import com.rare.server.service.result.UserGetFriendsResult;
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

	@Override
	public UserGetFriendsResult getFriendsOfUser(UserGetFriendsInput userGetFriendsInput) throws ServiceException {
		try {
			List<String> friends = this.getUserDao().getUserForGivenGoogleId(userGetFriendsInput.getGoogleId());
			return generateUserGetFriendsResult(userGetFriendsInput, friends);
		} catch (DaoException e) {
			throw new ServiceException(ServiceError.ERROR_IN_DATABASE, e);
		}
	}

	@Override
	public UserDeletionResult deleteUser(UserDeletionInput userInput) throws ServiceException {
		try {
			this.getUserDao().removeUser(userInput.getGoogleId());
			return generateUserDeletionResult();
		} catch (DaoException ex) {
			throw new ServiceException(ServiceError.ERROR_IN_DATABASE, ex);
		}
	}

	private UserDeletionResult generateUserDeletionResult() {
		UserDeletionResult result = new UserDeletionResult();
		result.setMessage(ServiceConstants.SUCCESSFULLY_DELETED_USER);
		return result;
	}

	private UserGetFriendsResult generateUserGetFriendsResult(UserGetFriendsInput userGetFriendsInput,
			List<String> friends) {
		UserGetFriendsResult result = new UserGetFriendsResult();
		result.setFriendNames(friends);
		result.setGoogleId(userGetFriendsInput.getGoogleId());
		return result;
	}

	private UserRegistrationResult generateSuccessServiceResult() {
		UserRegistrationResult result = new UserRegistrationResult();
		result.setMessage(ServiceConstants.SUCCESSFULLY_REGISTERED_USER);
		return result;
	}

	private UserRegistrationDaoInput generateDaoInput(UserRegistrationServiceInput userInput) {
		UserRegistrationDaoInput userRegistrationDaoInput = new UserRegistrationDaoInput();
		BeanUtils.copyProperties(userInput, userRegistrationDaoInput);
		userRegistrationDaoInput.setUserFriendDaoInputs(getUserFriendDaos(userInput));
		return userRegistrationDaoInput;
	}

	private List<UserFriendDaoInput> getUserFriendDaos(UserRegistrationServiceInput userInput) {
		List<UserFriendDaoInput> userFriendDaos = new ArrayList<>();
		for (int i = 0; i < userInput.getUserFriendInputs().size(); i++) {
			UserFriendDaoInput userFriendDaoInput = new UserFriendDaoInput();
			BeanUtils.copyProperties(userInput.getUserFriendInputs().get(i), userFriendDaoInput);
			userFriendDaos.add(userFriendDaoInput);
			userFriendDaoInput = null;
		}
		/*for (UserFriendServiceInput userFriend : userInput.getUserFriendInputs()) {
			UserFriendDaoInput userFriendDaoInput = new UserFriendDaoInput();
			BeanUtils.copyProperties(userFriend, userFriendDaoInput);
			userFriendDaos.add(userFriendDaoInput);
		}*/
		return userFriendDaos;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
