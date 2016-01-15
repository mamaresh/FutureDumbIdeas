package com.rare.server.apicaller.result;

import java.util.List;

public class UserFriendResult {

	public String title;

	public Integer totalItems;

	public List<UserIndividualFriends> items;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public List<UserIndividualFriends> getItems() {
		return items;
	}

	public void setItems(List<UserIndividualFriends> items) {
		this.items = items;
	}

}
