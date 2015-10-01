package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.Service;

public class ServiceDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceDAOImpl.class);

	public void addService(Service service) throws DatabaseException {
		LOG.debug("In #ServiceDAOImpl #addSerivce #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(service);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding service to database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceDAOImpl #addService #end");
	}

	public void deleteService(Service service) throws DatabaseException {
		LOG.debug("In #ServiceDAOImpl #deleteSerivce #start");
		try {
			em = getEntityManager();
			Service serviceToBeDeleted = em.find(Service.class, service.getId());
			if (serviceToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(serviceToBeDeleted);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting service from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceDAOImpl #deleteSerivce #end");
	}

	public void updateService(Service service) throws DatabaseException {
		LOG.debug("In #ServiceDAOImpl #updateSerivce #start");
		try {
			em = getEntityManager();
			Service serviceToBeUpdated = em.find(Service.class, service.getId());
			if (serviceToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(service);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating service in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceDAOImpl #updateSerivce #end");
	}

	public Service getServiceByServiceId(String id) throws DatabaseException {
		LOG.debug("In #ServiceDAOImpl #getSerivceByServiceId #start");
		Service service = null;
		try {
			em = getEntityManager();
			service = em.find(Service.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting service by Id ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceDAOImpl #getSerivceByServiceId #end");
		return service;
	}

}
