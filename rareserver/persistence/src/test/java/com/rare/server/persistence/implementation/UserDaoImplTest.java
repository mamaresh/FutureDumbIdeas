package com.rare.server.persistence.implementation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
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

import com.rare.server.persistence.UserDao;
import com.rare.server.persistence.common.Constants;
import com.rare.server.persistence.entity.User;
import com.rare.server.persistence.input.UserFriendDaoInput;
import com.rare.server.persistence.input.UserRegistrationDaoInput;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ CassandraUnitDependencyInjectionTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { Constants.USER_CQL })
@EmbeddedCassandra
@ContextConfiguration(locations = Constants.APPLICATION_CONTEXT_PATH)
public class UserDaoImplTest {

	@Autowired
	UserDao userDao;

	User user;

	UserRegistrationDaoInput userRegistrationDaoInput;
	UserFriendDaoInput userFriendDaoInput;
	List<UserFriendDaoInput> userFriendDaoInputs;

	@Before
	public void setUp() throws Exception {

		userFriendDaoInput = new UserFriendDaoInput();
		userFriendDaoInput.setDisplayName(Constants.FRIEND_NAME);
		userFriendDaoInput.setId(Constants.FRIEND_GOOGLE_ID);
		userFriendDaoInput.setUrl(Constants.FRIEND_GOOGLE_URL);

		userFriendDaoInputs = new ArrayList<>();
		userFriendDaoInputs.add(userFriendDaoInput);

		userRegistrationDaoInput = new UserRegistrationDaoInput();
		userRegistrationDaoInput.setDisplayName(Constants.DISPLAY_NAME);
		userRegistrationDaoInput.setGender(Constants.GENDER);
		userRegistrationDaoInput.setTotalItems(Constants.TOTAL_ITEMS);
		userRegistrationDaoInput.setUrl(Constants.URL);
		userRegistrationDaoInput.setUserFriends(userFriendDaoInputs);

		user = new User();
		user.setFriendGoogleId(Constants.FRIEND_GOOGLE_ID);
		user.setFriendGoogleUrl(Constants.FRIEND_GOOGLE_URL);
		user.setFriendName(Constants.FRIEND_NAME);
		user.setGender(Constants.GENDER);
		user.setGoogleId(Constants.GOOGLE_ID);
		user.setDisplayName(Constants.DISPLAY_NAME);
		user.setTotalItems(Constants.TOTAL_ITEMS);
		user.setUrl(Constants.URL);
	}

	@After
	public void tearDown() throws Exception {

		userRegistrationDaoInput = null;
		userFriendDaoInput = null;
		userFriendDaoInputs = null;

	}

	@Test
	public void test() throws Exception {
		this.getUserDao().insertUser(userRegistrationDaoInput);
		assertThat(this.getUserDao().getUser(Constants.GOOGLE_ID, Constants.FRIEND_GOOGLE_ID), equalTo(user));
		assertThat(this.getUserDao().getUserForGivenGoogleId(Constants.GOOGLE_ID), notNullValue());
		this.getUserDao().removeUser(Constants.GOOGLE_ID);
		assertThat(this.getUserDao().getUser(Constants.GOOGLE_ID, Constants.FRIEND_GOOGLE_ID), nullValue());

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
