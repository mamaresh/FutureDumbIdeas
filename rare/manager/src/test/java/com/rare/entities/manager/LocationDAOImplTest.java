package com.rare.entities.manager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Location;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class LocationDAOImplTest {

	LocationDAOImpl locationDAOImpl;
	Location location, returnLocation;
	String id;

	@Before
	public void setUp() throws Exception {
		locationDAOImpl = new LocationDAOImpl();
		location = new Location();

		id = DatabaseConstants.ID;

		location.setAddress(DatabaseConstants.ADDRESS);
		location.setCity(DatabaseConstants.CITY);
		location.setCountry(DatabaseConstants.COUNTRY);
		location.setId(id);
		location.setLatitude(DatabaseConstants.LATITUDE);
		location.setLongitude(DatabaseConstants.LONGITUDE);
		location.setState(DatabaseConstants.STATE);
		location.setZipcode(DatabaseConstants.ZIPCODE);
	}

	@After
	public void tearDown() throws Exception {
		locationDAOImpl = null;
		location = null;
		id = null;
	}

	@Test
	public void test() {
		try {
			locationDAOImpl.addLocation(location);
			returnLocation = locationDAOImpl.getLocationForId(id);
			Assert.assertNotNull(returnLocation);
			location.setLatitude(DatabaseConstants.UPDATED_LATITUDE);
			locationDAOImpl.updateLocation(location);
			returnLocation = locationDAOImpl.getLocationForId(id);
			Assert.assertEquals(DatabaseConstants.UPDATED_LATITUDE, returnLocation.getLatitude());
			locationDAOImpl.deleteLocation(location);
			returnLocation = locationDAOImpl.getLocationForId(id);
			Assert.assertNull(returnLocation);
		} catch (DatabaseException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
