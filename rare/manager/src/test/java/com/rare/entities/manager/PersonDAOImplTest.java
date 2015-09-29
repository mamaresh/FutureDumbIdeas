package com.rare.entities.manager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Location;
import com.rare.entities.entity.Person;
import com.rare.entities.entity.SocialNetworking;

public class PersonDAOImplTest {

	PersonDAOImpl personDAOImpl;
	Person person, returnPerson;
	Location location;
	SocialNetworking socialNetworking;
	String ID;

	@Before
	public void setUp() throws Exception {
		personDAOImpl = new PersonDAOImpl();

		person = new Person();
		location = new Location();
		socialNetworking = new SocialNetworking();

		ID = DatabaseConstants.ID;

		location.setAddress(DatabaseConstants.ADDRESS);
		location.setCity(DatabaseConstants.CITY);
		location.setCountry(DatabaseConstants.COUNTRY);
		location.setId(ID);
		location.setLatitude(DatabaseConstants.LATITUDE);
		location.setLongitude(DatabaseConstants.LONGITUDE);
		location.setState(DatabaseConstants.STATE);
		location.setZipcode(DatabaseConstants.ZIPCODE);

		socialNetworking.setFacebookId(DatabaseConstants.FACEBOOKID);
		socialNetworking.setGoogleId(DatabaseConstants.GOOGLEID);
		socialNetworking.setId(ID);
		socialNetworking.setTwitterId(DatabaseConstants.TWITTERID);

		person.setAge(DatabaseConstants.AGE);
		person.setFirstName(DatabaseConstants.FIRSTNAME);
		person.setGender(DatabaseConstants.GENDER);
		person.setId(ID);
		person.setInitials(DatabaseConstants.INITIALS);
		person.setLastName(DatabaseConstants.LASTNAME);
		person.setLocation(location);
		person.setSocialnetworking(socialNetworking);
	}

	@After
	public void tearDown() throws Exception {
		personDAOImpl = null;
		person = null;
		location = null;
		socialNetworking = null;
	}

	@Test
	public void test() {
		try {
			personDAOImpl.addPerson(person);
			returnPerson = personDAOImpl.getPersonById(ID);
			Assert.assertNotNull(returnPerson);
			person.setAge(DatabaseConstants.UPDATED_AGE);
			personDAOImpl.updatePerson(person);
			returnPerson = personDAOImpl.getPersonById(ID);
			Assert.assertEquals(DatabaseConstants.UPDATED_AGE,
					returnPerson.getAge());
			personDAOImpl.deletePerson(person);
			returnPerson = personDAOImpl.getPersonById(ID);
			Assert.assertNull(returnPerson);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
