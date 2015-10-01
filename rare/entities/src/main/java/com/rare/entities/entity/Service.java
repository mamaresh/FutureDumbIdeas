package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.rare.commons.constants.DatabaseConstants;

import java.util.List;

/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@Table(name = DatabaseConstants.SERVICE_TABLE)
@NamedQuery(name = DatabaseConstants.FIND_ALL_SERVICES, query = "SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ID, name = DatabaseConstants.ID)
	private String id;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_DESCRIPTION, name = DatabaseConstants.DESCRIPTION)
	private String description;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_NAME, name = DatabaseConstants.NAME)
	private String name;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_PHONE_NUMBER, name = DatabaseConstants.PHONE_NUMBER)
	private String phoneNumber;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_PROFILE_CONTENT, name = DatabaseConstants.PROFILE_CONTENT)
	private String profileContent;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_WEBSITE_URL, name = DatabaseConstants.WEBSITE_URL)
	private String websiteUrl;

	// bi-directional many-to-one association to Metric
	@OneToMany(mappedBy = DatabaseConstants.MAPPEDBY_SERVICE)
	private List<Metric> metrics;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = DatabaseConstants.JOINCOLUMN_PERSONID, nullable = false)
	private Person person;

	// bi-directional many-to-one association to ServiceCategory
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = DatabaseConstants.JOINCOLUMN_ID, referencedColumnName = DatabaseConstants.REFERENCE_COLUMN_SERVICEID, nullable = false, insertable = false, updatable = false)
	private ServiceCategory serviceCategory;

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

	public ServiceCategory getServicecategory() {
		return this.serviceCategory;
	}

	public void setServicecategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

}