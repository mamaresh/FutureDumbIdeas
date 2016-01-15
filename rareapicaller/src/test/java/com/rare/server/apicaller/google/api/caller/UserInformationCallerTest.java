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
import com.rare.server.apicaller.result.UserInformationResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ApiCallerTestConstants.APPLICATION_CONTEXT_PATH)
public class UserInformationCallerTest {

	@Autowired
	UserInformationCaller userInformationCaller;

	UserInformationInput input;
	UserInformationResult result;

	@Before
	public void setUp() {
		input = new UserInformationInput();
		input.setGoogleId(ApiCallerTestConstants.GOOGLE_ID);
		input.setAccessToken("ya29.XgKCSKbXu_LXYBnIjBgXiqXNeR-Z2ErWb9jUHLAVzgtJVbvKJqTMFfRZ1SKQNJqeIqQpIDw");

		result = new UserInformationResult();
		result.setDisplayName(ApiCallerTestConstants.DISPLAY_NAME);
		result.setGender(ApiCallerTestConstants.GENDER);
		result.setUrl(ApiCallerTestConstants.GOOGLE_PLUS_URL);
		result.setObjectType(ApiCallerTestConstants.OBJECT_TYPE);
	}

	@After
	public void tearDown() {
		input = null;
	}

	@Test
	public void test() throws Exception {
		this.getUserInformationCaller().callGoogleApiToGetPersonalInformation(input);
	}

	//@Test
	public void testGetFriendsInformation() throws Exception {
		this.getUserInformationCaller().getFriendsInformation(input);
	}

	public UserInformationCaller getUserInformationCaller() {
		return userInformationCaller;
	}

	public void setUserInformationCaller(UserInformationCaller userInformationCaller) {
		this.userInformationCaller = userInformationCaller;
	}

}
