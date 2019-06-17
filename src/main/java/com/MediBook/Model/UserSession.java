package com.MediBook.Model;

import java.util.UUID;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")

public class UserSession {

	
	private final String id = UUID.randomUUID().toString();
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public UserSession(User user) {
		super();
		this.user = user;
	}
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
