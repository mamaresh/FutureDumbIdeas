package com.rare.logic;

import org.springframework.beans.factory.annotation.Autowired;

import com.rare.entities.entity.Authentication;
import com.rare.entities.manager.AuthenticationDAOImpl;

public class UserHandler {

	@Autowired
	AuthenticationDAOImpl authenticationDAOImpl;

	Authentication authentication;

	public boolean checkCredentials(String userName, String password) {
		authentication = this.getAuthenticationDAOImpl().getAuthentication(
				userName);
		if (authentication.getPassword().equals(password))
			return true;
		else
			return false;
	}

	public AuthenticationDAOImpl getAuthenticationDAOImpl() {
		return authenticationDAOImpl;
	}

	public void setAuthenticationDAOImpl(
			AuthenticationDAOImpl authenticationDAOImpl) {
		this.authenticationDAOImpl = authenticationDAOImpl;
	}

}
