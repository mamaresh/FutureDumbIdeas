package com.rare.entities.manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Authentication;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class AuthenticationDAOImplTest {

	AuthenticationDAOImpl authenticationDAOImpl;
	Authentication authentication, returnAuthentication;
	String id;

	@Before
	public void setUp() {
		authenticationDAOImpl = new AuthenticationDAOImpl();
		authentication = new Authentication();

		id = DatabaseConstants.ID;

		authentication.setId(id);
		authentication.setPassword(DatabaseConstants.PASSWORD);
		authentication.setLastUpdated(DatabaseConstants.LASTUPDATED);
	}

	@Test
	public void test() {
		try {
			authenticationDAOImpl.addAuthentication(authentication);
			returnAuthentication = authenticationDAOImpl.getAuthentication(id);
			Assert.assertNotNull(returnAuthentication);
			authentication.setPassword(DatabaseConstants.UPDATED_PASSWORD);
			authentication.setLastUpdated(DatabaseConstants.LASTUPDATED);
			authenticationDAOImpl.updateAuthentication(authentication);
			returnAuthentication = authenticationDAOImpl.getAuthentication(id);
			Assert.assertEquals(DatabaseConstants.UPDATED_PASSWORD,
					returnAuthentication.getPassword());
			authenticationDAOImpl.deleteAuthentication(authentication);
			returnAuthentication = authenticationDAOImpl.getAuthentication(id);
			Assert.assertNull(returnAuthentication);
		} catch (DatabaseException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
