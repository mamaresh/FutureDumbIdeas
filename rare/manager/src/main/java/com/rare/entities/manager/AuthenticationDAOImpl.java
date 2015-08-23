package com.rare.entities.manager;

import com.rare.entities.entity.Authentication;

public class AuthenticationDAOImpl extends AbstractEntityManager {

	public void addAuthentication(Authentication authentication) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(authentication);
			em.getTransaction().commit();
			em.getEntityManagerFactory().getCache().evictAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeEntityManager();
		}
	}

	public Authentication getAuthentication(String userName) {
		Authentication authentication = null;
		try {
			em = getEntityManager();
			authentication = em.find(Authentication.class, userName);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeEntityManager();
		}
		return authentication;
	}

	public void deleteAuthentication(Authentication authentication) {
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
			ex.printStackTrace();
		} finally {
			closeEntityManager();
		}
	}

}
