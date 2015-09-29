package com.rare.entities.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.rare.commons.constants.DatabaseConstants;

/**
 * The persistent class for the authentication database table.
 * 
 */
@Entity
@Table(name = DatabaseConstants.AUTHENTICATION_TABLE)
@NamedQuery(name = DatabaseConstants.FIND_ALL_AUTHENTICATION, query = "SELECT a FROM Authentication a")
public class Authentication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_ID, name = DatabaseConstants.ID)
	private String id;

	@Column(nullable = false, name = DatabaseConstants.LASTUPDATED)
	private Timestamp lastUpdated;

	@Column(nullable = false, length = DatabaseConstants.LENGTH_OF_PASSWORD, name = DatabaseConstants.PASSWORD)
	private String password;

	public Authentication() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}