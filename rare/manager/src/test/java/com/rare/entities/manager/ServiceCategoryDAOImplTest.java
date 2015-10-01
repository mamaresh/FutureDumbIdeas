package com.rare.entities.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.test.constants.DatabaseConstants;
import com.rare.entities.entity.Service;
import com.rare.entities.entity.ServiceCategory;

@ContextConfiguration(locations = { "classpath:src/test/resources/META-INF/persistence.xml" })
public class ServiceCategoryDAOImplTest {

	ServiceCategoryDAOImpl serviceCategoryDAOImpl;
	ServiceCategory serviceCategory, returnServiceCategory;
	Service service;
	List<Service> services;
	String serviceCategoryId;
	String serviceId;

	@Before
	public void setUp() throws Exception {
		serviceCategoryDAOImpl = new ServiceCategoryDAOImpl();
		serviceCategory = new ServiceCategory();
		service = new Service();
		services = new ArrayList<Service>();

		serviceId = DatabaseConstants.ID;
		service.setId(serviceId);
		service.setDescription(DatabaseConstants.SERVICE_DESCRIPTION);
		service.setName(DatabaseConstants.SERVICE_NAME);
		service.setPhoneNumber(DatabaseConstants.PHONE_NUMBER);
		service.setProfileContent(DatabaseConstants.PROFILE_CONTENT);
		service.setWebsiteUrl(DatabaseConstants.WEBSITE_URL);

		services.add(service);

		serviceCategoryId = DatabaseConstants.ID;

		serviceCategory.setDescription(DatabaseConstants.SERVICE_CATEGORY_DESCRIPTION);
		serviceCategory.setName(DatabaseConstants.SERVICE_NAME);
		serviceCategory.setId(serviceCategoryId);
		serviceCategory.setServices(services);
	}

	@After
	public void tearDown() throws Exception {
		serviceCategoryDAOImpl = null;
		serviceCategory = null;
		service = null;
		services = null;
	}

	@Test
	public void test() {
		try {
			serviceCategoryDAOImpl.addServiceCategory(serviceCategory);
			returnServiceCategory = serviceCategoryDAOImpl.getServiceCategoryById(serviceCategoryId);
			Assert.assertNotNull(returnServiceCategory);
			serviceCategory.setDescription(DatabaseConstants.UPDATED_SERVICE_CATEGORY_DESCRIPTION);
			serviceCategoryDAOImpl.updateServiceCategory(serviceCategory);
			returnServiceCategory = serviceCategoryDAOImpl.getServiceCategoryById(serviceCategoryId);
			Assert.assertEquals(DatabaseConstants.UPDATED_SERVICE_CATEGORY_DESCRIPTION,
					returnServiceCategory.getDescription());
			serviceCategoryDAOImpl.deleteServiceCategory(serviceCategory);
			returnServiceCategory = serviceCategoryDAOImpl.getServiceCategoryById(serviceCategoryId);
		} catch (DatabaseException ex) {
			Assert.fail(ex.getMessage());
		}
	}

}
