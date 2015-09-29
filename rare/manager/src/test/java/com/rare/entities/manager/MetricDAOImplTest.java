package com.rare.entities.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Metric;

import junit.framework.Assert;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class MetricDAOImplTest {

	MetricDAOImpl metricDAOImpl;
	Metric metric, returnMetric;
	String id;

	@Before
	public void setUp() throws Exception {
		metricDAOImpl = new MetricDAOImpl();
		metric = new Metric();

		id = DatabaseConstants.ID;

		metric.setDescription(DatabaseConstants.METRIC_DESCRIPTION);
		metric.setId(id);
		metric.setName(DatabaseConstants.METRIC_NAME);
	}

	@After
	public void tearDown() throws Exception {
		id = null;
		metric = null;
		metricDAOImpl = null;
	}

	@Test
	public void test() {
		try {
			metricDAOImpl.addMetric(metric);
			returnMetric = metricDAOImpl.getMetricById(id);
			Assert.assertNotNull(returnMetric);
			metric.setDescription(DatabaseConstants.UPDATED_METRIC_DESCRIPTION);
			metricDAOImpl.updateMetric(metric);
			returnMetric = metricDAOImpl.getMetricById(id);
			Assert.assertEquals(DatabaseConstants.UPDATED_METRIC_DESCRIPTION, metric.getDescription());
			metricDAOImpl.deleteMetric(metric);
			returnMetric = metricDAOImpl.getMetricById(id);
			Assert.assertNull(returnMetric);
		} catch (DatabaseException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
