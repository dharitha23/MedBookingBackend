package com.MediBook.Model;

public class User {
 public String email;
 public String password;
 public String type;
 public int userID;
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
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getuserID() {
	return userID;
}
public void setuserID(int userID) {
	this.userID = userID;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
}
