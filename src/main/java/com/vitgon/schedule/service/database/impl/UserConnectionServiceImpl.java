package com.vitgon.schedule.service.database.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dao.auth.UserConnectionDao;
import com.vitgon.schedule.model.database.auth.UserConnection;
import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;
import com.vitgon.schedule.service.database.UserConnectionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class UserConnectionServiceImpl implements UserConnectionService {
	
	private UserConnectionDao userConnectionDao;

	@Override
	public UserConnection save(UserConnection obj) {
		return userConnectionDao.save(obj);
	}

	@Override
	public UserConnection update(UserConnection obj) {
		return userConnectionDao.save(obj);
	}

	@Override
	public UserConnection findById(UserConnectionId id) {
		return userConnectionDao.findById(id).orElse(null);
	}

	@Override
	public List<UserConnection> findAll() {
		return userConnectionDao.findAll();
	}

	@Override
	public void delete(UserConnection obj) {
		userConnectionDao.delete(obj);
	}

	@Override
	public void deleteById(UserConnectionId id) {
		userConnectionDao.deleteById(id);
	}

	@Override
	public UserConnection findByProviderIdAndProviderUserId(String providerId, String providerUserId) {
		return userConnectionDao.findByProviderIdAndProviderUserId(providerId, providerUserId);
	}
}
