package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the metrics database table.
 * 
 */
@Entity
@Table(name="metrics")
@NamedQuery(name="Metric.findAll", query="SELECT m FROM Metric m")
public class Metric implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false, length=100)
	private String description;

	@Column(nullable=false, length=100)
	private String name;

	@Column(nullable=false, length=100)
	private String serviceCategoryId;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="Id", referencedColumnName="metricId", nullable=false, insertable=false, updatable=false)
	private Service service;

	//bi-directional one-to-one association to Rating
	@OneToOne(mappedBy="metric")
	private Rating rating;

	public Metric() {
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

	public String getServiceCategoryId() {
		return this.serviceCategoryId;
	}

	public void setServiceCategoryId(String serviceCategoryId) {
		this.serviceCategoryId = serviceCategoryId;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

}