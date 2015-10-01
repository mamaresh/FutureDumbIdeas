package com.rare.logic;

import org.codehaus.jettison.json.JSONObject;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rare.commons.exception.LogicException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.commons.test.constants.LogicConstants;
import com.rare.entities.entity.Person;
import com.rare.entities.manager.PersonDAOImpl;

public class PersonHandlerTest {

	PersonHandler personHandler;
	JSONObject personJson;
	PersonDAOImpl personDAOImpl;

	@Before
	public void setUp() throws Exception {
		personHandler = new PersonHandler();
		personJson = new JSONObject();
		personDAOImpl = EasyMock.createMock(PersonDAOImpl.class);

		personJson.put(LogicConstants.AGE, DatabaseConstants.AGE);
		personJson.put(LogicConstants.FIRSTNAME, DatabaseConstants.FIRSTNAME);
		personJson.put(LogicConstants.GENDER, DatabaseConstants.GENDER);
		personJson.put(LogicConstants.INITIALS, DatabaseConstants.INITIALS);
		personJson.put(LogicConstants.LASTNAME, DatabaseConstants.LASTNAME);
		personJson.put(LogicConstants.ADDRESS, DatabaseConstants.ADDRESS);
		personJson.put(LogicConstants.CITY, DatabaseConstants.CITY);
		personJson.put(LogicConstants.COUNTRY, DatabaseConstants.COUNTRY);
		personJson.put(LogicConstants.LATITUDE, DatabaseConstants.LATITUDE);
		personJson.put(LogicConstants.LONGITUDE, DatabaseConstants.LONGITUDE);
		personJson.put(LogicConstants.STATE, DatabaseConstants.STATE);
		personJson.put(LogicConstants.ZIPCODE, DatabaseConstants.ZIPCODE);
		personJson.put(LogicConstants.FACEBOOK, DatabaseConstants.FACEBOOKID);
		personJson.put(LogicConstants.GOOGLE, DatabaseConstants.GOOGLEID);
		personJson.put(LogicConstants.TWITTER, DatabaseConstants.TWITTERID);

		personDAOImpl.addPerson(EasyMock.anyObject(Person.class));
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(personDAOImpl);

		personHandler.setPersonDAOImpl(personDAOImpl);
	}

	@After
	public void tearDown() throws Exception {
		personHandler = null;
		personJson = null;
		personDAOImpl = null;
	}

	@Test
	public void test() {
		try {
			personHandler.addPerson(personJson);
		} catch (LogicException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
