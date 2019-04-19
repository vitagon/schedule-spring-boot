package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dao.auth.UserDao;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.auth.UserConnection;
import com.vitgon.schedule.service.database.UserConnectionService;
import com.vitgon.schedule.service.database.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private PasswordEncoder passwordEncoder;
	private UserConnectionService userConnectionService;
	
	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		return userDao.save(user);
	}

	@Override
	public User update(User user) {
		return userDao.save(user);
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id).get();
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findBySpecificRoles(List<String> roles) {
		return userDao.findBySpecificRoles(roles);
	}

	@Override
	public void delete(User obj) {
		userDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	public User createUser(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		
		// find by provider and provider user id
		UserConnection userConnection = userConnectionService.findByProviderIdAndProviderUserId(
				key.getProviderId(), key.getProviderUserId());
		User user = null;
		if (userConnection != null) {
			user = findById(Integer.parseInt(userConnection.getUserId()));
			
			if (user != null) {
				return user;
			}
		}
		
		user = new User();
		UserProfile userProfile = connection.fetchUserProfile();
		
		// TODO: create new user
		return null;
	}
}
