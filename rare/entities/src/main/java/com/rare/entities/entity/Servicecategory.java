package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servicecategory database table.
 * 
 */
@Entity
@Table(name="servicecategory")
@NamedQuery(name="Servicecategory.findAll", query="SELECT s FROM Servicecategory s")
public class Servicecategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false, length=5000)
	private String description;

	@Column(nullable=false, length=1000)
	private String name;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="servicecategory")
	private List<Service> services;

	public Servicecategory() {
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