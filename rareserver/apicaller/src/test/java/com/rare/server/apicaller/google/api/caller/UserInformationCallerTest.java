package com.rare.server.apicaller.google.api.caller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rare.server.apicaller.UserInformationCaller;
import com.rare.server.apicaller.google.api.constants.ApiCallerTestConstants;
import com.rare.server.apicaller.input.UserInformationInput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ApiCallerTestConstants.APPLICATION_CONTEXT_PATH)
public class UserInformationCallerTest {

	@Autowired
	UserInformationCaller userInformationCaller;

	UserInformationInput input;

	@Before
	public void setUp() {
		input = new UserInformationInput();
		input.setGoogleId(ApiCallerTestConstants.GOOGLE_ID);
	}

	@After
	public void tearDown() {
		input = null;
	}

	@Test
	public void test() throws Exception {
		this.getUserInformationCaller().callGoogleApiToGetPersonalInformation(input);
	}

	public UserInformationCaller getUserInformationCaller() {
		return userInformationCaller;
	}

	public void setUserInformationCaller(UserInformationCaller userInformationCaller) {
		this.userInformationCaller = userInformationCaller;
	}

}
