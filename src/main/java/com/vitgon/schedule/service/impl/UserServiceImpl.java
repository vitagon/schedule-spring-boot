package com.vitgon.schedule.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dao.auth.RoleDao;
import com.vitgon.schedule.dao.auth.UserDao;
import com.vitgon.schedule.model.auth.Role;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.UserService;

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
		Role userRole = roleDao.findByRole("MANAGER");
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
}
