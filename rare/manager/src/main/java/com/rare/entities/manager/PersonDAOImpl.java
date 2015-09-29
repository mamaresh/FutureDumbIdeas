package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.Person;

public class PersonDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonDAOImpl.class);

	public void addPerson(Person person) throws DatabaseException {
		LOG.debug("In #PersonDAOImpl #addPerson #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding person to database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #PersonDAOImpl #addPerson #end");
	}

	public void deletePerson(Person person) throws DatabaseException {
		LOG.debug("In #PersonDAOImpl #deletePerson #start");
		try {
			em = getEntityManager();
			Person personToBeDeleted = em.find(Person.class, person.getId());
			if (personToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(personToBeDeleted);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting person from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #PersonDAOImpl #deletePerson #end");
	}

	public void updatePerson(Person person) throws DatabaseException {
		LOG.debug("In #PersonDAOImpl #deletePerson #start");
		try {
			em = getEntityManager();
			Person personToBeUpdated = em.find(Person.class, person.getId());
			if (personToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(person);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating person in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #PersonDAOImpl #deletePerson #end");
	}

	public Person getPersonById(String id) throws DatabaseException {
		LOG.debug("In #PersonDAOImpl #getPersonById #start");
		Person person = null;
		try {
			em = getEntityManager();
			person = em.find(Person.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting person by Id ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		return person;
	}

}
