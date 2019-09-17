package com.vitgon.schedule.service.database.impl.translation;

import com.vitgon.schedule.dao.translation.UserTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;
import com.vitgon.schedule.projection.UserProjection;
import com.vitgon.schedule.service.database.translation.UserTranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserTranslationServiceImpl implements UserTranslationService {

	private final UserTranslationDao userTranslDao;

	@Autowired
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
	public Optional<UserTranslation> findById(UserTranslationId id) {
		return userTranslDao.findById(id);
	}

	@Override
	public List<UserTranslation> findAll() {
		return userTranslDao.findAll();
	}
	
	@Override
	public UserTranslation findByLocaleAndUser(Locale locale, User user) {
		return userTranslDao.findByUserTranslationIdLocaleAndUserTranslationIdUser(locale, user);
	}
	
	@Override
	public void delete(UserTranslation obj) {
		userTranslDao.delete(obj);
	}

	@Override
	public void deleteById(UserTranslationId id) {
		userTranslDao.deleteById(id);
	}

	@Override
	public Optional<UserTranslation> findByLocaleIdAndUserId(Integer localeId, Integer userId) {
		return userTranslDao.findByLocaleIdAndUserId(localeId, userId);
	}

	@Override
	public UserProjection getUserTranslationProjectionByLocaleIdAndUserId(Integer localeId, Integer userId) {
		return userTranslDao.getUserTranslationProjectionByLocaleIdAndUserId(localeId, userId);
	}
}
