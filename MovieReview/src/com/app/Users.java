package com.app;

import java.util.Date;

public class Users {
	private int userId;
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String mobile;
	private Date dob;
	
	public Users() {
	
	}

	public Users(int userId, String fName, String lName, String email, String password, String mobile, Date dob) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.dob = dob;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", password="
				+ password + ", mobile=" + mobile + ", dob=" + dob + "]";
	}
	
	
	
}
