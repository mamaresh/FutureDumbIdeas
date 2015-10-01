package com.rare.entities.manager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Rating;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class RatingDAOImplTest {

	RatingDAOImpl ratingDAOImpl;
	Rating rating, returnRating;
	String id;

	@Before
	public void setUp() throws Exception {
		ratingDAOImpl = new RatingDAOImpl();
		rating = new Rating();

		id = DatabaseConstants.ID;

		rating.setDescription(DatabaseConstants.RATING_DESCRIPTION);
		rating.setId(id);
		rating.setStars(DatabaseConstants.RATING_STARS);
		rating.setName(DatabaseConstants.RATING_NAME);
	}

	@After
	public void tearDown() throws Exception {
		rating = null;
		ratingDAOImpl = null;
	}

	@Test
	public void test() {
		try {
			ratingDAOImpl.addRating(rating);
			returnRating = ratingDAOImpl.getRatingById(id);
			Assert.assertNotNull(returnRating);
			rating.setStars(DatabaseConstants.UPDATED_RATING_STARS);
			ratingDAOImpl.updateRating(rating);
			returnRating = ratingDAOImpl.getRatingById(id);
			ratingDAOImpl.deleteRating(rating);
			returnRating = ratingDAOImpl.getRatingById(id);
			Assert.assertNull(returnRating);
		} catch (DatabaseException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
