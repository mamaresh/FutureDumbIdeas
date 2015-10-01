package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.rare.commons.constants.DatabaseConstants;

/**
 * The persistent class for the rating database table.
 * 
 */
@Entity
@Table(name = DatabaseConstants.RATING_TABLE)
@NamedQuery(name = DatabaseConstants.FIND_ALL_RATING, query = "SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ID, name = DatabaseConstants.ID)
	private String id;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_DESCRIPTION, name = DatabaseConstants.DESCRIPTION)
	private String description;

	@Column(nullable = false, name = DatabaseConstants.STARS)
	private float stars;
	
	@Column(nullable = false, name = DatabaseConstants.NAME, length = DatabaseConstants.LENGTH_OF_NAME)
	private String name;

	// bi-directional one-to-one association to Metric
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = DatabaseConstants.JOINCOLUMN_ID, referencedColumnName = DatabaseConstants.REFERENCE_COLUMN_RATINGID, nullable = false, insertable = false, updatable = false)
	private Metric metric;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "personId", nullable = false)
	private Person person;

	public Rating() {
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

	public float getStars() {
		return this.stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Metric getMetric() {
		return this.metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}