package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.auth.RoleDao;
import com.vitgon.schedule.model.database.auth.Role;
import com.vitgon.schedule.service.database.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role save(Role obj) {
		return roleDao.save(obj);
	}

	@Override
	public Role update(Role obj) {
		return roleDao.save(obj);
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return roleDao.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findByRole(String role) {
		return roleDao.findByRole(role);
	}
	
	@Override
	public void delete(Role obj) {
		roleDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		roleDao.deleteById(id);
	}
}
