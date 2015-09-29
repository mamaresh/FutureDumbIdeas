package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name="person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false)
	private int age;

	@Column(nullable=false, length=100)
	private String firstName;

	@Column(nullable=false, length=100)
	private String gender;

	@Column(nullable=false, length=100)
	private String initials;

	@Column(nullable=false, length=100)
	private String lastName;

	//bi-directional one-to-one association to Location
	@OneToOne
	@JoinColumn(name="ID", nullable=false, insertable=false, updatable=false)
	private Location location;

	//bi-directional one-to-one association to Socialnetworking
	@OneToOne
	@JoinColumn(name="ID", nullable=false, insertable=false, updatable=false)
	private Socialnetworking socialnetworking;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="person")
	private List<Rating> ratings;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="person")
	private List<Service> services;

	public Person() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Socialnetworking getSocialnetworking() {
		return this.socialnetworking;
	}

	public void setSocialnetworking(Socialnetworking socialnetworking) {
		this.socialnetworking = socialnetworking;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setPerson(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setPerson(null);

		return rating;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setPerson(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setPerson(null);

		return service;
	}

}