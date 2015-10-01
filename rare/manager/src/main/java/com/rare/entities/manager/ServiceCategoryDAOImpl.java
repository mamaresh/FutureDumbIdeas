package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.ServiceCategory;

public class ServiceCategoryDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceCategoryDAOImpl.class);

	public void addServiceCategory(ServiceCategory serviceCategory) throws DatabaseException {
		LOG.debug("In #ServiceCategoryDAOImpl #addServiceCategory #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(serviceCategory);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding sevice category ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceCategoryDAOImpl #addServiceCategory #end");
	}

	public void deleteServiceCategory(ServiceCategory serviceCategory) throws DatabaseException {
		LOG.debug("In #ServiceCategoryDAOImpl #deleteServiceCategory #start");
		try {
			em = getEntityManager();
			ServiceCategory serviceCategoryToBeDeleted = em.find(ServiceCategory.class, serviceCategory.getId());
			if (serviceCategoryToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(serviceCategoryToBeDeleted);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting service category ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceCategoryDAOImpl #deleteServiceCategory #end");
	}

	public void updateServiceCategory(ServiceCategory serviceCategory) throws DatabaseException {
		LOG.debug("In #ServiceCategoryDAOImpl #updateServiceCategory #start");
		try {
			em = getEntityManager();
			ServiceCategory serviceCategoryToBeUpdated = em.find(ServiceCategory.class, serviceCategory.getId());
			if (serviceCategoryToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(serviceCategory);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating service category ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceCategoryDAOImpl #updateServiceCategory #end");
	}

	public ServiceCategory getServiceCategoryById(String id) throws DatabaseException {
		LOG.debug("In #ServiceCategoryDAOImpl #getServiceCategoryById #start");
		ServiceCategory serviceCategory = null;
		try {
			em = getEntityManager();
			serviceCategory = em.find(ServiceCategory.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting service category by Id ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #ServiceCategoryDAOImpl #getServiceCategoryById #end");
		return serviceCategory;
	}

}
