package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.Authentication;

public class AuthenticationDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(AuthenticationDAOImpl.class);

	public void addAuthentication(Authentication authentication)
			throws DatabaseException {
		LOG.debug("In #AuthenticationDAOImpl #addAuthentication #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(authentication);
			em.getTransaction().commit();
			em.getEntityManagerFactory().getCache().evictAll();
		} catch (Exception ex) {
			LOG.error("Error in adding authentication to database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #AuthenticationDAOImpl #addAuthentication #end");
	}

	public Authentication getAuthentication(String userName)
			throws DatabaseException {
		LOG.debug("In #AuthenticationDAOImpl #getAuthentication #start");
		Authentication authentication = null;
		try {
			em = getEntityManager();
			authentication = em.find(Authentication.class, userName);
		} catch (Exception ex) {
			LOG.error("Error in getting authentication from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #AuthenticationDAOImpl #getAuthentication #end");
		return authentication;
	}

	public void deleteAuthentication(Authentication authentication)
			throws DatabaseException {
		LOG.debug("In #AuthenticationDAOImpl #deleteAuthentication #start");
		try {
			em = getEntityManager();
			Authentication authenticationToBeRemoved = em.find(
					Authentication.class, authentication.getUserId());
			if (authenticationToBeRemoved != null) {
				em.getTransaction().begin();
				em.remove(authenticationToBeRemoved);
				em.getTransaction().commit();
				em.getEntityManagerFactory().getCache().evictAll();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting authentication from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #AuthenticationDAOImpl #deleteAuthentication #end");
	}

	public void updateAuthentication(Authentication authentication)
			throws DatabaseException {
		LOG.debug("In #AuthenticationDAOImpl #updateAuthentication #start");
		try {
			em = getEntityManager();
			Authentication authenticationToBeDeleted = em.find(
					Authentication.class, authentication.getUserId());
			if (authenticationToBeDeleted != null) {
				em.getTransaction().begin();
				em.merge(authentication);
				em.getTransaction().commit();
				em.getEntityManagerFactory().getCache().evictAll();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating authentication from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #AuthenticationDAOImpl #updateAuthentication #end");
	}

}
