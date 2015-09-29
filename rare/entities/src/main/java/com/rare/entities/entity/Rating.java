package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rating database table.
 * 
 */
@Entity
@Table(name="rating")
@NamedQuery(name="Rating.findAll", query="SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false, length=1000)
	private String description;

	@Column(nullable=false, length=100)
	private String metricId;

	@Column(nullable=false)
	private float stars;

	@Column(nullable=false)
	private int weightage;

	//bi-directional one-to-one association to Metric
	@OneToOne
	@JoinColumn(name="Id", referencedColumnName="RatingId", nullable=false, insertable=false, updatable=false)
	private Metric metric;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId", nullable=false)
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

	public String getMetricId() {
		return this.metricId;
	}

	public void setMetricId(String metricId) {
		this.metricId = metricId;
	}

	public float getStars() {
		return this.stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}

	public int getWeightage() {
		return this.weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
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