package com.rare.server.service.implementation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.rare.server.service.UserRegistrationService;
import com.rare.server.service.common.ServiceTestConstants;
import com.rare.server.service.global.ServiceConstants;
import com.rare.server.service.input.UserDeletionInput;
import com.rare.server.service.input.UserFriendServiceInput;
import com.rare.server.service.input.UserGetFriendsInput;
import com.rare.server.service.input.UserRegistrationServiceInput;
import com.rare.server.service.result.UserDeletionResult;
import com.rare.server.service.result.UserGetFriendsResult;
import com.rare.server.service.result.UserRegistrationResult;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ CassandraUnitDependencyInjectionTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { ServiceTestConstants.USER_CQL })
@EmbeddedCassandra
@ContextConfiguration(locations = ServiceTestConstants.APPLICATION_CONTEXT_PATH)
public class UserRegistrationServiceImplTest {

	@Autowired
	UserRegistrationService service;

	UserRegistrationServiceInput input;
	UserFriendServiceInput friendInput;
	List<UserFriendServiceInput> userFriendServiceInputs;
	UserGetFriendsResult result;
	UserGetFriendsInput getFriendsInput;
	List<String> friendNames;
	UserDeletionInput userDeletionInput;
	UserDeletionResult userDeletionResult;
	UserRegistrationResult userRegistrationResult;

	@Before
	public void setUp() {

		userDeletionInput = new UserDeletionInput();
		userDeletionInput.setGoogleId(ServiceTestConstants.GOOGLE_ID);

		userRegistrationResult = new UserRegistrationResult();
		userRegistrationResult.setMessage(ServiceConstants.SUCCESSFULLY_REGISTERED_USER);

		userDeletionResult = new UserDeletionResult();
		userDeletionResult.setMessage(ServiceConstants.SUCCESSFULLY_DELETED_USER);

		getFriendsInput = new UserGetFriendsInput();
		getFriendsInput.setGoogleId(ServiceTestConstants.GOOGLE_ID);

		friendNames = new ArrayList<>();
		friendNames.add(ServiceTestConstants.FRIEND_NAME);

		result = new UserGetFriendsResult();
		result.setGoogleId(ServiceTestConstants.GOOGLE_ID);
		result.setFriendNames(friendNames);

		friendInput = new UserFriendServiceInput();
		friendInput.setDisplayName(ServiceTestConstants.FRIEND_NAME);
		friendInput.setId(ServiceTestConstants.FRIEND_GOOGLE_ID);
		friendInput.setUrl(ServiceTestConstants.FRIEND_GOOGLE_URL);

		userFriendServiceInputs = new ArrayList<>();
		userFriendServiceInputs.add(friendInput);

		input = new UserRegistrationServiceInput();

		input.setDisplayName(ServiceTestConstants.DISPLAY_NAME);
		input.setGender(ServiceTestConstants.GENDER);
		input.setGoogleId(ServiceTestConstants.GOOGLE_ID);
		input.setTotalItems(ServiceTestConstants.TOTAL_ITEMS);
		input.setUrl(ServiceTestConstants.URL);
		input.setUserFriendInputs(userFriendServiceInputs);
	}

	@After
	public void tearDown() {
		input = null;
		userFriendServiceInputs = null;
		friendInput = null;
	}

	@Test
	public void test() throws Exception {
		assertThat(this.getService().registerUser(input), equalTo(userRegistrationResult));
		assertThat(this.getService().getFriendsOfUser(getFriendsInput), equalTo(result));
		assertThat(this.getService().deleteUser(userDeletionInput), equalTo(userDeletionResult));
	}

	public UserRegistrationService getService() {
		return service;
	}

	public void setService(UserRegistrationService service) {
		this.service = service;
	}

}
