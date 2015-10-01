package com.rare.entities.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Metric;
import com.rare.entities.entity.Service;
import com.rare.entities.entity.ServiceCategory;

import junit.framework.Assert;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class ServiceDAOImplTest {

	ServiceDAOImpl serviceDAOImpl;
	Service service, returnService;
	List<Metric> metrics;
	Metric metricOne, metricTwo;
	ServiceCategory serviceCategory;
	String serviceId, serviceCategoryId;
	String metricOneId, metricTwoId;

	@Before
	public void setUp() throws Exception {
		serviceDAOImpl = new ServiceDAOImpl();
		service = new Service();

		serviceId = DatabaseConstants.ID;
		metricOneId = DatabaseConstants.ID;
		metricTwoId = DatabaseConstants.ID;

		metricOne = new Metric();
		metricOne.setDescription(DatabaseConstants.METRIC_DESCRIPTION);
		metricOne.setId(metricOneId);
		metricOne.setName(DatabaseConstants.METRIC_NAME);
		metricOne.setWeightage(DatabaseConstants.WEIGHTAGE);

		metricTwo = new Metric();
		metricTwo.setDescription(DatabaseConstants.METRIC_DESCRIPTION);
		metricTwo.setId(metricTwoId);
		metricTwo.setName(DatabaseConstants.METRIC_NAME);
		metricTwo.setWeightage(DatabaseConstants.WEIGHTAGE);

		metrics = new ArrayList<Metric>();
		metrics.add(metricOne);
		metrics.add(metricTwo);

		serviceCategoryId = DatabaseConstants.ID;

		serviceCategory = new ServiceCategory();
		serviceCategory.setId(serviceCategoryId);
		serviceCategory.setDescription(DatabaseConstants.SERVICE_CATEGORY_DESCRIPTION);
		serviceCategory.setName(DatabaseConstants.SERVICE_NAME);

		service.setDescription(DatabaseConstants.SERVICE_DESCRIPTION);
		service.setId(serviceId);
		service.setMetrics(metrics);
		service.setName(DatabaseConstants.SERVICE_NAME);
		service.setPhoneNumber(DatabaseConstants.PHONE_NUMBER);
		service.setProfileContent(DatabaseConstants.PROFILE_CONTENT);
		service.setServicecategory(serviceCategory);
		service.setWebsiteUrl(DatabaseConstants.WEBSITE_URL);
	}

	@After
	public void tearDown() throws Exception {
		serviceDAOImpl = null;
		service = null;
		returnService = null;
		metrics = null;
		metricOne = null;
		metricTwo = null;
		serviceCategory = null;
		serviceId = null;
		metricOneId = null;
		metricTwoId = null;
		serviceCategoryId = null;
	}

	@Test
	public void test() {
		try {
			serviceDAOImpl.addService(service);
			returnService = serviceDAOImpl.getServiceByServiceId(serviceId);
			Assert.assertNotNull(returnService);
			service.setPhoneNumber(DatabaseConstants.UPDATED_PHONE_NUMBER);
			serviceDAOImpl.updateService(service);
			returnService = serviceDAOImpl.getServiceByServiceId(serviceId);
			Assert.assertEquals(DatabaseConstants.UPDATED_PHONE_NUMBER, returnService.getPhoneNumber());
			serviceDAOImpl.deleteService(service);
			returnService = serviceDAOImpl.getServiceByServiceId(serviceId);
			Assert.assertNull(returnService);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
