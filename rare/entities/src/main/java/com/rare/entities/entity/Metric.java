package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.rare.commons.constants.DatabaseConstants;

/**
 * The persistent class for the metrics database table.
 * 
 */
@Entity
@Table(name = DatabaseConstants.METRICS_TABLE)
@NamedQuery(name = DatabaseConstants.FIND_ALL_METRICS, query = "SELECT m FROM Metric m")
public class Metric implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ID, name = DatabaseConstants.ID)
	private String id;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_DESCRIPTION, name = DatabaseConstants.DESCRIPTION)
	private String description;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_NAME, name = DatabaseConstants.NAME)
	private String name;
	
	@Column(nullable = false, name = DatabaseConstants.WEIGHTAGE)
	int weightage;

	// bi-directional many-to-one association to Service
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = DatabaseConstants.JOINCOLUMN_ID, referencedColumnName = DatabaseConstants.REFERENCED_COLUMN_METRICID, nullable = false, insertable = false, updatable = false)
	private Service service;

	// bi-directional one-to-one association to Rating
	@OneToOne(mappedBy = DatabaseConstants.MAPPEDBY_METRIC)
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

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}

}