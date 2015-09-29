package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@Table(name="service")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false, length=5000)
	private String description;

	@Column(nullable=false, length=100)
	private String name;

	@Column(nullable=false, length=13)
	private String phoneNumber;

	@Column(nullable=false, length=5000)
	private String profileContent;

	@Column(nullable=false, length=1000)
	private String websiteUrl;

	//bi-directional many-to-one association to Metric
	@OneToMany(mappedBy="service")
	private List<Metric> metrics;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId", nullable=false)
	private Person person;

	public Service() {
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfileContent() {
		return this.profileContent;
	}

	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}

	public String getWebsiteUrl() {
		return this.websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public List<Metric> getMetrics() {
		return this.metrics;
	}

	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

	public Metric addMetric(Metric metric) {
		getMetrics().add(metric);
		metric.setService(this);

		return metric;
	}

	public Metric removeMetric(Metric metric) {
		getMetrics().remove(metric);
		metric.setService(null);

		return metric;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}