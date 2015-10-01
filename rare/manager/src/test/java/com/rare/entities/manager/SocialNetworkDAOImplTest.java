package com.rare.entities.manager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.SocialNetwork;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class SocialNetworkDAOImplTest {

	SocialNetworkDAOImpl socialNetworkDAOImpl;
	SocialNetwork socialNetwork, returnSocialNetwork;
	String id;

	@Before
	public void setUp() throws Exception {
		socialNetworkDAOImpl = new SocialNetworkDAOImpl();
		socialNetwork = new SocialNetwork();

		id = DatabaseConstants.ID;

		socialNetwork.setId(id);
		socialNetwork.setFacebook(DatabaseConstants.FACEBOOKID);
		socialNetwork.setGoogle(DatabaseConstants.GOOGLEID);
		socialNetwork.setTwitter(DatabaseConstants.TWITTERID);
	}

	@After
	public void tearDown() throws Exception {
		socialNetwork = null;
		socialNetworkDAOImpl = null;
	}

	@Test
	public void test() {
		try {
			socialNetworkDAOImpl.addSocialNetwork(socialNetwork);
			returnSocialNetwork = socialNetworkDAOImpl.getSocialNetworkById(id);
			Assert.assertNotNull(returnSocialNetwork);
			socialNetwork.setFacebook(DatabaseConstants.UPDATED_FACEBOOK_ID);
			socialNetworkDAOImpl.updateSocialNetwork(socialNetwork);
			returnSocialNetwork = socialNetworkDAOImpl.getSocialNetworkById(id);
			socialNetworkDAOImpl.deleteSocialNetwork(socialNetwork);
			returnSocialNetwork = socialNetworkDAOImpl.getSocialNetworkById(id);
			Assert.assertNull(returnSocialNetwork);
		} catch (DatabaseException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
