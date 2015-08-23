package com.rare.entities.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the authentication database table.
 * 
 */
@Entity
@Table(name="authentication")
@NamedQuery(name="Authentication.findAll", query="SELECT a FROM Authentication a")
public class Authentication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Timestamp lastUpdated;

	@Column(nullable=false, length=50)
	private String password;

	@Id
	@Column(nullable=false, length=50)
	private String userId;

	public Authentication() {
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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}