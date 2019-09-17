package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.auth.UserConnectionDao;
import com.vitgon.schedule.model.database.auth.UserConnection;
import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;
import com.vitgon.schedule.service.database.UserConnectionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserConnectionServiceImpl implements UserConnectionService {
	
	private UserConnectionDao userConnectionDao;

	public UserConnectionServiceImpl(UserConnectionDao userConnectionDao) {
		this.userConnectionDao = userConnectionDao;
	}

	@Override
	public UserConnection save(UserConnection obj) {
		return userConnectionDao.save(obj);
	}

	@Override
	public UserConnection update(UserConnection obj) {
		return userConnectionDao.save(obj);
	}

	@Override
	public Optional<UserConnection> findById(UserConnectionId id) {
		return userConnectionDao.findById(id);
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
