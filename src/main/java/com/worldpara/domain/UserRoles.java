package com.worldpara.domain;

import java.util.List;

public class UserRoles {

	private User user;
	private List<String> roleLists;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoleLists() {
		return roleLists;
	}

	public void setRoleLists(List<String> roleLists) {
		this.roleLists = roleLists;
	}
}
