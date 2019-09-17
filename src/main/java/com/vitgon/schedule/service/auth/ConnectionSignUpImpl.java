package com.vitgon.schedule.service.auth;

import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

public class ConnectionSignUpImpl implements ConnectionSignUp {
	
	private UserService usersService;

	public ConnectionSignUpImpl(UserService usersService) {
		this.usersService = usersService;
	}

	@Override
	public String execute(Connection<?> connection) {
		User user = usersService.createUser(connection);
		return user.getUsername();
	}	
}
