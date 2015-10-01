package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.rare.commons.constants.DatabaseConstants;

import java.util.List;

/**
 * The persistent class for the servicecategory database table.
 * 
 */
@Entity
@Table(name = DatabaseConstants.SERVICECATEGORY_TABLE)
@NamedQuery(name = DatabaseConstants.FIND_ALL_SERVICE_CATEGORIES, query = "SELECT s FROM ServiceCategory s")
public class ServiceCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ID, name = DatabaseConstants.ID)
	private String id;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_DESCRIPTION, name = DatabaseConstants.DESCRIPTION)
	private String description;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_NAME, name = DatabaseConstants.NAME)
	private String name;

	// bi-directional many-to-one association to Service
	@OneToMany(mappedBy = DatabaseConstants.MAPPEDBY_SERVICECATEGORY)
	private List<Service> services;

	public ServiceCategory() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setServicecategory(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setServicecategory(null);

		return service;
	}

}