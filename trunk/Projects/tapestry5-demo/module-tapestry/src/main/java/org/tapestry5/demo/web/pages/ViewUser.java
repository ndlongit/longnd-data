package org.tapestry5.demo.web.pages;

import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.User;
import org.java.demo.service.UserService;

public class ViewUser {

	@Inject
	// @Service(UserService.SERVICE_ID)
	private UserService userService;

	private long userId;

	public User getUser() {
		return userService.find(userId);
	}

	@SetupRender
	void setupRender() {
	}

	void onActivate(long userId) {
		this.userId = userId;
	}
}
