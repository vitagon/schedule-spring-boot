package com.vitgon.schedule.service.database.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dao.auth.RoleDao;
import com.vitgon.schedule.dao.auth.UserDao;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.model.database.auth.Role;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private RoleDao roleDao;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserDao userDao,
						   RoleDao roleDao,
						   BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleDao.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
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
}
