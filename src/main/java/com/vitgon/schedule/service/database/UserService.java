package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.model.auth.Role;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.database.base.Service;

public interface UserService extends Service<User, Integer> {
	User findByEmail(String email);
	List<User> findBySpecificRoles(List<String> roles);
}
