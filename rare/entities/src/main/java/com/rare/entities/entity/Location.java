package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.rare.commons.constants.DatabaseConstants;

/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@Table(name = "location")
@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ID, name = DatabaseConstants.ID)
	private String id;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ADDRESS, name = DatabaseConstants.ADDRESS)
	private String address;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_CITY, name = DatabaseConstants.CITY)
	private String city;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_COUNTRY, name = DatabaseConstants.COUNTRY)
	private String country;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_LATITUDE, name = DatabaseConstants.LATITUDE)
	private String latitude;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_LONGITUDE, name = DatabaseConstants.LONGITUDE)
	private String longitude;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_STATE, name = DatabaseConstants.STATE)
	private String state;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ZIPCODE, name = DatabaseConstants.ZIPCODE)
	private String zipcode;

	// bi-directional one-to-one association to Person
	@OneToOne(mappedBy = DatabaseConstants.MAPPEDBY_LOCATION, cascade = { CascadeType.ALL })
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