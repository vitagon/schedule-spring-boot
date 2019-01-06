package com.vitgon.schedule.service;

import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.base.Service;

public interface UserService extends Service<User, Integer> {
	User findByEmail(String email);
}
