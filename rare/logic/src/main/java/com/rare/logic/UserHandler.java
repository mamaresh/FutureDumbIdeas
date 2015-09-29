package com.rare.logic;

import org.springframework.beans.factory.annotation.Autowired;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.exception.LogicException;
import com.rare.entities.entity.Authentication;
import com.rare.entities.manager.AuthenticationDAOImpl;

public class UserHandler {

	@Autowired
	AuthenticationDAOImpl authenticationDAOImpl;

	Authentication authentication;

	public boolean checkCredentials(String userName, String password)
			throws LogicException {
		try {
			authentication = this.getAuthenticationDAOImpl().getAuthentication(
					userName);
			if (authentication.getPassword().equals(password))
				return true;
			else
				return false;
		} catch (DatabaseException ex) {
			throw new LogicException(ex);
		} catch (Exception ex) {
			throw new LogicException(ex);
		}
	}

	public AuthenticationDAOImpl getAuthenticationDAOImpl() {
		return authenticationDAOImpl;
	}

	public void setAuthenticationDAOImpl(
			AuthenticationDAOImpl authenticationDAOImpl) {
		this.authenticationDAOImpl = authenticationDAOImpl;
	}

}
