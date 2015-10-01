package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.Rating;

public class RatingDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(RatingDAOImpl.class);

	public void addRating(Rating rating) throws DatabaseException {
		LOG.debug("In #RatingDAOImpl #addRating #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(rating);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding rating to database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #RatingDAOImpl #addRating #end");
	}

	public void deleteRating(Rating rating) throws DatabaseException {
		LOG.debug("In #RatingDAOImpl #deleteRating #start");
		try {
			em = getEntityManager();
			Rating ratingToBeDeleted = em.find(Rating.class, rating.getId());
			if (ratingToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(rating);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting rating from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #RatingDAOImpl #deleteRating #end");
	}

	public void updateRating(Rating rating) throws DatabaseException {
		LOG.debug("In #RatingDAOImpl #updateRating #start");
		try {
			em = getEntityManager();
			Rating ratingToBeUpdated = em.find(Rating.class, rating.getId());
			if (ratingToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(rating);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating rating in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #RatingDAOImpl #updateRating #end");
	}

	public Rating getRatingById(String id) throws DatabaseException {
		LOG.debug("In #RatingDAOImpl #getRatingById #start");
		Rating rating = null;
		try {
			em = getEntityManager();
			rating = em.find(Rating.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting rating from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #RatingDAOImpl #getRatingById #end");
		return rating;
	}

}
