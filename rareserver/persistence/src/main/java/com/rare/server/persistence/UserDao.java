package com.rare.server.persistence;

import java.util.List;

import com.rare.server.persistence.entity.User;
import com.rare.server.persistence.exception.DaoException;
import com.rare.server.persistence.input.UserRegistrationDaoInput;

public interface UserDao {

	public void insertUser(UserRegistrationDaoInput input) throws DaoException;

	public List<String> getUserForGivenGoogleId(String googleId) throws DaoException;

	public User getUser(String googleId, String googleFriendId) throws DaoException;

	public void removeUser(String googleId) throws DaoException;

}
