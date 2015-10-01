package com.rare.entities.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractEntityManager.class);

	private static EntityManagerFactory emf;
	static EntityManager em = null;
	private String persistenceUnitName = "rare";

	public EntityManager getEntityManager() {
		LOG.debug("In #AbstractEntityManager #getEntityManager #start");
		if (em == null) {
			emf = Persistence.createEntityManagerFactory(persistenceUnitName);
			em = emf.createEntityManager();
		} else {
			if (!em.isOpen())
				em = emf.createEntityManager();
		}
		LOG.debug("In #AbstractEntityManager #getEntityManager #end");
		return em;
	}

	public void closeEntityManager() {
		LOG.debug("In #AbstractEntityManager #closeEntityManager #start");
		if (em != null && em.isOpen()) {
			em.close();
		}
		LOG.debug("In #AbstractEntityManager #closeEntityManager #end");
	}

}
