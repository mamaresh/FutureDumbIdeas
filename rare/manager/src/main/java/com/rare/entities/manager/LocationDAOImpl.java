package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.Location;

public class LocationDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(LocationDAOImpl.class);

	public void addLocation(Location location) throws DatabaseException {
		LOG.debug("In #LocationDAOImpl #addLocation #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(location);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding location to database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #LocationDAOImpl #addLocation #end");
	}

	public void deleteLocation(Location location) throws DatabaseException {
		LOG.debug("In #LocationDAOImpl #deleteLocation #start");
		try {
			em = getEntityManager();
			Location locationToBeDeleted = em.find(Location.class, location.getId());
			if (locationToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(locationToBeDeleted);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting location from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #LocationDAOImpl #deleteLocation #end");
	}

	public void updateLocation(Location location) throws DatabaseException {
		LOG.debug("In #LocationDAOImpl #updateLocation #start");
		try {
			em = getEntityManager();
			Location locationToBeUpdated = em.find(Location.class, location.getId());
			if (locationToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(location);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating location in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #LocationDAOImpl #updateLocation #end");
	}

	public Location getLocationForId(String id) throws DatabaseException {
		LOG.debug("In #LocationDAOImpl #getLocationForId #start");
		Location location = null;
		try {
			em = getEntityManager();
			location = em.find(Location.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting location for Id ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #LocationDAOImpl #getLocationForId #end");
		return location;
	}
}
