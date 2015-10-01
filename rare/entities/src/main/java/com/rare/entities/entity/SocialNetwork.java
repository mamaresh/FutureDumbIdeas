package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.rare.commons.constants.DatabaseConstants;

/**
 * The persistent class for the socialnetworking database table.
 * 
 */
@Entity
@Table(name = DatabaseConstants.SOCIAL_NETWORK_TABLE)
@NamedQuery(name = DatabaseConstants.FIND_ALL_SOCIAL_NETWORKS, query = "SELECT s FROM SocialNetwork s")
public class SocialNetwork implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, name = DatabaseConstants.ID, length = DatabaseConstants.LENGTH_OF_ID)
	private String id;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_FACEBOOK, name = DatabaseConstants.FACEBOOK)
	private String facebook;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_GOOGLE, name = DatabaseConstants.GOOGLE)
	private String google;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_TWITTER, name = DatabaseConstants.TWITTER)
	private String twitter;

	// bi-directional one-to-one association to Person
	@OneToOne(mappedBy = DatabaseConstants.MAPPEDBY_SOCIALNETWORK)
	private Person person;

	public SocialNetwork() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}