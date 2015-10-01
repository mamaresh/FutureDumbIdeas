package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.SocialNetwork;

public class SocialNetworkDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(SocialNetworkDAOImpl.class);

	public void addSocialNetwork(SocialNetwork socialNetwork) throws DatabaseException {
		LOG.debug("In #SocialNetworkDAOImpl #addSocialNetwork #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(socialNetwork);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding social network to database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #SocialNetworkDAOImpl #addSocialNetwork #end");
	}

	public void deleteSocialNetwork(SocialNetwork socialNetwork) throws DatabaseException {
		LOG.debug("In #SocialNetworkDAOImpl #deleteSocialNetwork #start");
		try {
			em = getEntityManager();
			SocialNetwork socialNetworkToBeDeleted = em.find(SocialNetwork.class, socialNetwork.getId());
			if (socialNetworkToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(socialNetwork);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting social network from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #SocialNetworkDAOImpl #deleteSocialNetwork #end");
	}

	public void updateSocialNetwork(SocialNetwork socialNetwork) throws DatabaseException {
		LOG.debug("In #SocialNetworkDAOImpl #updateSocialNetwork #start");
		try {
			em = getEntityManager();
			SocialNetwork socialNetworkToBeUpdated = em.find(SocialNetwork.class, socialNetwork.getId());
			if (socialNetworkToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(socialNetwork);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating social network in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #SocialNetworkDAOImpl #updateSocialNetwork #end");
	}

	public SocialNetwork getSocialNetworkById(String id) throws DatabaseException {
		LOG.debug("In #SocialNetworkDAOImpl #getSocialNetworkById #start");
		SocialNetwork socialNetwork = null;
		try {
			em = getEntityManager();
			socialNetwork = em.find(SocialNetwork.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting social network in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #SocialNetworkDAOImpl #getSocialNetworkById #end");
		return socialNetwork;
	}

}
