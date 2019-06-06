package com.vitgon.schedule.service.database.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	private HibernateSequenceService hibernateSequenceService;
	private PasswordEncoder encoder;
	
	@Override
	public List<User> findByEmail(String email) {
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
	public Optional<User> findById(Integer id) {
		return userDao.findById(id);
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
		Optional<User> userOpt = null;
		if (userConnection != null) {
			userOpt = findById(Integer.parseInt(userConnection.getUserId()));
			
			if (userOpt.isPresent()) {
				return userOpt.get();
			}
		}
		
		User user = new User();
		UserProfile userProfile = connection.fetchUserProfile();
		
		String randomPasswod = UUID.randomUUID().toString().substring(0, 6);
		String encryptedPassword = encoder.encode(randomPasswod);
		
		user.setUsername(UUID.randomUUID().toString().substring(0, 18));
		user.setEmail(userProfile.getEmail());
		user.setPassword("{bcrypt}"+encryptedPassword);
		user.setKeyFirstname(userProfile.getFirstName());
		user.setKeyLastname(userProfile.getLastName());
		user.setActive(true);
		user.setProviderId(key.getProviderId());
		user = save(user);
		
		// update username
		user.setUsername(generateUsername(user.getId()));
		user = update(user);
		
		return user;
	}
	
	public String generateUsername(Integer id) {
		return "user" + id.toString();
	}
	
	private Integer getNextVal() {
		Integer nextVal = hibernateSequenceService.getNextVal();
		if (nextVal <= 0) {
			try {
				throw new Exception("nextVal from hibernate_sequence cannot be less than 0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return nextVal;
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByEmailAndProviderId(String email, String providerId) {
		return userDao.findByEmailAndProviderId(email, providerId);
	}
}
