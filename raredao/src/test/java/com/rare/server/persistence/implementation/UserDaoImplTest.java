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
import com.rare.server.persistence.common.DaoTestConstants;
import com.rare.server.persistence.entity.User;
import com.rare.server.persistence.input.UserFriendDaoInput;
import com.rare.server.persistence.input.UserRegistrationDaoInput;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ CassandraUnitDependencyInjectionTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { DaoTestConstants.USER_CQL })
@EmbeddedCassandra
@ContextConfiguration(locations = DaoTestConstants.APPLICATION_CONTEXT_PATH)
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
		userFriendDaoInput.setDisplayName(DaoTestConstants.FRIEND_NAME);
		userFriendDaoInput.setId(DaoTestConstants.FRIEND_GOOGLE_ID);
		userFriendDaoInput.setUrl(DaoTestConstants.FRIEND_GOOGLE_URL);

		userFriendDaoInputs = new ArrayList<>();
		userFriendDaoInputs.add(userFriendDaoInput);

		userRegistrationDaoInput = new UserRegistrationDaoInput();
		userRegistrationDaoInput.setDisplayName(DaoTestConstants.DISPLAY_NAME);
		userRegistrationDaoInput.setGender(DaoTestConstants.GENDER);
		userRegistrationDaoInput.setTotalItems(DaoTestConstants.TOTAL_ITEMS);
		userRegistrationDaoInput.setUrl(DaoTestConstants.URL);
		userRegistrationDaoInput.setUserFriendDaoInputs(userFriendDaoInputs);
		userRegistrationDaoInput.setGoogleId(DaoTestConstants.GOOGLE_ID);

		user = new User();
		user.setFriendGoogleId(DaoTestConstants.FRIEND_GOOGLE_ID);
		user.setFriendGoogleUrl(DaoTestConstants.FRIEND_GOOGLE_URL);
		user.setFriendName(DaoTestConstants.FRIEND_NAME);
		user.setGender(DaoTestConstants.GENDER);
		user.setGoogleId(DaoTestConstants.GOOGLE_ID);
		user.setDisplayName(DaoTestConstants.DISPLAY_NAME);
		user.setTotalItems(DaoTestConstants.TOTAL_ITEMS);
		user.setUrl(DaoTestConstants.URL);
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
		assertThat(this.getUserDao().getUser(DaoTestConstants.GOOGLE_ID, DaoTestConstants.FRIEND_GOOGLE_ID), equalTo(user));
		assertThat(this.getUserDao().getUserForGivenGoogleId(DaoTestConstants.GOOGLE_ID), notNullValue());
		this.getUserDao().removeUser(DaoTestConstants.GOOGLE_ID);
		assertThat(this.getUserDao().getUser(DaoTestConstants.GOOGLE_ID, DaoTestConstants.FRIEND_GOOGLE_ID), nullValue());

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
