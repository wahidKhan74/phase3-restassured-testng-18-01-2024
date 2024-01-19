package com.simplilearn.data.model;

public class UserInfo {
	
	public String username;
	public String password;
	
	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + "]";
	}
	
}
