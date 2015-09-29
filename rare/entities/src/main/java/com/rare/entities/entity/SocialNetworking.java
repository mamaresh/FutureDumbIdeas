package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the socialnetworking database table.
 * 
 */
@Entity
@Table(name="socialnetworking")
@NamedQuery(name="SocialNetworking.findAll", query="SELECT s FROM SocialNetworking s")
public class SocialNetworking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=100)
	private String id;

	@Column(nullable=false, length=100)
	private String facebookId;

	@Column(nullable=false, length=100)
	private String googleId;

	@Column(nullable=false, length=100)
	private String twitterId;

	//bi-directional one-to-one association to Person
	@OneToOne(mappedBy="socialnetworking")
	private Person person;

	public SocialNetworking() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGoogleId() {
		return this.googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getTwitterId() {
		return this.twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}