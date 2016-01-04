package com.rare.server.persistence.entity;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.rare.server.persistence.global.PersistenceConstants;

@Table(name = PersistenceConstants.USER_TABLE)
public class User {

	@PartitionKey
	@Column(name = PersistenceConstants.GOOGLE_ID)
	private String googleId;

	@ClusteringColumn
	@Column(name = PersistenceConstants.FRIEND_GOOGLE_ID)
	private String friendGoogleId;

	@Column(name = PersistenceConstants.GENDER)
	private String gender;

	@Column(name = PersistenceConstants.URL)
	private String url;

	@Column(name = PersistenceConstants.TOTAL_FRIENDS)
	private Integer totalItems;

	@Column(name = PersistenceConstants.FRIEND_GOOGLE_URL)
	private String friendGoogleUrl;

	@Column(name = PersistenceConstants.FRIEND_NAME)
	private String friendName;

	@Column(name = PersistenceConstants.NAME)
	private String displayName;
	
	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getFriendGoogleId() {
		return friendGoogleId;
	}

	public void setFriendGoogleId(String friendGoogleId) {
		this.friendGoogleId = friendGoogleId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public String getFriendGoogleUrl() {
		return friendGoogleUrl;
	}

	public void setFriendGoogleUrl(String friendGoogleUrl) {
		this.friendGoogleUrl = friendGoogleUrl;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((friendGoogleId == null) ? 0 : friendGoogleId.hashCode());
		result = prime * result + ((friendGoogleUrl == null) ? 0 : friendGoogleUrl.hashCode());
		result = prime * result + ((friendName == null) ? 0 : friendName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((googleId == null) ? 0 : googleId.hashCode());
		result = prime * result + ((totalItems == null) ? 0 : totalItems.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (friendGoogleId == null) {
			if (other.friendGoogleId != null)
				return false;
		} else if (!friendGoogleId.equals(other.friendGoogleId))
			return false;
		if (friendGoogleUrl == null) {
			if (other.friendGoogleUrl != null)
				return false;
		} else if (!friendGoogleUrl.equals(other.friendGoogleUrl))
			return false;
		if (friendName == null) {
			if (other.friendName != null)
				return false;
		} else if (!friendName.equals(other.friendName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (googleId == null) {
			if (other.googleId != null)
				return false;
		} else if (!googleId.equals(other.googleId))
			return false;
		if (totalItems == null) {
			if (other.totalItems != null)
				return false;
		} else if (!totalItems.equals(other.totalItems))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
