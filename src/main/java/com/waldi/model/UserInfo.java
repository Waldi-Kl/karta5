package com.waldi.model;

public class UserInfo {
	
	private int userId;
	private String userName;
	private String userSurname;
	private String login;
	private String pass;
	private String email; 
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userSurname, String password) {
        this.userSurname = userSurname.trim().toUpperCase();
        this.pass = password;
    }

	public int getId() {
		return userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getName() {
		return userName;
	}

	public void setName(String name) {		
		this.userName = name.trim().toUpperCase();
	}

	public String getSurname() {
		return userSurname;
	}

	public void setSurname(String surname) {
		this.userSurname = surname.trim().toUpperCase();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.trim().toUpperCase();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

}
