package com.rare.entities.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractEntityManager {

	private static EntityManagerFactory emf;
	static EntityManager em = null;
	private String persistenceUnitName = "rare";

	public EntityManager getEntityManager() {
		if (em == null) {
			emf = Persistence.createEntityManagerFactory(persistenceUnitName);
			em = emf.createEntityManager();
		} else {
			if (!em.isOpen())
				em = emf.createEntityManager();
		}
		return em;
	}

	public void closeEntityManager() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

}
