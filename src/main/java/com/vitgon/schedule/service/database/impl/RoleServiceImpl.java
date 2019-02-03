package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.auth.RoleDao;
import com.vitgon.schedule.model.auth.Role;
import com.vitgon.schedule.service.database.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role save(Role obj) {
		return roleDao.save(obj);
	}

	@Override
	public Role update(Role obj) {
		return roleDao.save(obj);
	}

	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id).get();
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findByRole(String role) {
		return roleDao.findByRole(role);
	}
}
