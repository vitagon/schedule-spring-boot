package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.UserTranslationDao;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.UserTranslation;
import com.vitgon.schedule.model.translation.pk.UserTranslationId;
import com.vitgon.schedule.service.database.translation.UserTranslationService;

@Service
@Transactional
public class UserTranslationServiceImpl implements UserTranslationService {

	@Autowired
	private final UserTranslationDao userTranslDao;
	
	public UserTranslationServiceImpl(UserTranslationDao userTranslDao) {
		this.userTranslDao = userTranslDao;
	}
	
	@Override
	public UserTranslation save(UserTranslation obj) {
		return userTranslDao.save(obj);
	}

	@Override
	public UserTranslation update(UserTranslation obj) {
		return userTranslDao.save(obj);
	}

	@Override
	public UserTranslation findById(UserTranslationId id) {
		return userTranslDao.findById(id).get();
	}

	@Override
	public List<UserTranslation> findAll() {
		return userTranslDao.findAll();
	}
	
	@Override
	public UserTranslation findByLocaleAndUser(Locale locale, User user) {
		return userTranslDao.findByUserTranslationIdLocaleAndUserTranslationIdUser(locale, user);
	}
}
