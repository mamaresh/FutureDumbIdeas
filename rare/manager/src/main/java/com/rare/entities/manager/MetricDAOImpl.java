package com.rare.entities.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.entities.entity.Metric;

public class MetricDAOImpl extends AbstractEntityManager {

	private static final Logger LOG = LoggerFactory.getLogger(MetricDAOImpl.class);

	public void addMetric(Metric metric) throws DatabaseException {
		LOG.debug("In #MetricDAOImpl #addMetric #start");
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(metric);
			em.getTransaction().commit();
		} catch (Exception ex) {
			LOG.error("Error in adding metric ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #MetricDAOImpl #addMetric #end");
	}

	public void deleteMetric(Metric metric) throws DatabaseException {
		LOG.debug("In #MetricDAOImpl #deleteMetric #start");
		try {
			em = getEntityManager();
			Metric metricToBeDeleted = em.find(Metric.class, metric.getId());
			if (metricToBeDeleted != null) {
				em.getTransaction().begin();
				em.remove(metricToBeDeleted);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in deleting metric from database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #MetricDAOImpl #deleteMetric #end");
	}

	public void updateMetric(Metric metric) throws DatabaseException {
		LOG.debug("In #MetricDAOImpl #updateMetric #start");
		try {
			em = getEntityManager();
			Metric metricToBeUpdated = em.find(Metric.class, metric.getId());
			if (metricToBeUpdated != null) {
				em.getTransaction().begin();
				em.merge(metric);
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			LOG.error("Error in updating metric in database ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		LOG.debug("In #MetricDAOImpl #updateMetric #end");
	}

	public Metric getMetricById(String id) throws DatabaseException {
		LOG.debug("In #MetricDAOImpl #getMetricById #start");
		Metric metric = null;
		try {
			em = getEntityManager();
			metric = em.find(Metric.class, id);
		} catch (Exception ex) {
			LOG.error("Error in getting metric by Id ", ex);
			throw new DatabaseException(ex);
		} finally {
			closeEntityManager();
		}
		return metric;
	}

}
