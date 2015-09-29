package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@Table(name="location")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false, length=100)
	private String address;

	@Column(nullable=false, length=100)
	private String city;

	@Column(nullable=false, length=100)
	private String country;

	@Column(nullable=false, length=100)
	private String latitude;

	@Column(nullable=false, length=100)
	private String longitude;

	@Column(nullable=false, length=100)
	private String state;

	@Column(nullable=false, length=100)
	private String zipcode;

	//bi-directional one-to-one association to Person
	@OneToOne(mappedBy="location", cascade={CascadeType.ALL})
	private Person person;

	public Location() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}