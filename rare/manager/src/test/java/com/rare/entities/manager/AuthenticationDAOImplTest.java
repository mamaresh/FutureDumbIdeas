package com.rare.entities.manager;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rare.entities.entity.Authentication;
import com.rare.entities.manager.AuthenticationDAOImpl;

public class AuthenticationDAOImplTest {

	AuthenticationDAOImpl authenticationDAOImpl;
	Authentication authentication, returnAuthentication;
	String userName, password;
	Timestamp lastUpdated;

	@Before
	public void setUp() {
		authenticationDAOImpl = new AuthenticationDAOImpl();
		authentication = new Authentication();

		userName = "JunitUser";
		password = "JunitPassword";
		lastUpdated = new Timestamp(Calendar.getInstance().getTime().getTime());

		authentication.setUserId(userName);
		authentication.setPassword(password);
		authentication.setLastUpdated(lastUpdated);
	}

	@Test
	public void test() {
		authenticationDAOImpl.addAuthentication(authentication);
		returnAuthentication = authenticationDAOImpl
				.getAuthentication(userName);
		Assert.assertNotNull(returnAuthentication);
		authenticationDAOImpl.deleteAuthentication(authentication);
		returnAuthentication = authenticationDAOImpl
				.getAuthentication(userName);
		Assert.assertNull(returnAuthentication);
	}

}
