package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;
import com.vitgon.schedule.service.database.translation.MajorTranslationService;

@Service
@Transactional
public class MajorTranslationServiceImpl implements MajorTranslationService {

	@Autowired
	private final MajorTranslationDao majorTranslDao;
	
	public MajorTranslationServiceImpl(MajorTranslationDao majorTranslDao) {
		this.majorTranslDao = majorTranslDao;
	}
	
	@Override
	public MajorTranslation save(MajorTranslation obj) {
		return majorTranslDao.save(obj);
	}

	@Override
	public MajorTranslation update(MajorTranslation obj) {
		return majorTranslDao.save(obj);
	}

	@Override
	public Optional<MajorTranslation> findById(MajorTranslationId id) {
		return majorTranslDao.findById(id);
	}

	@Override
	public List<MajorTranslation> findAll() {
		return majorTranslDao.findAll();
	}
	
	@Override
	public void delete(MajorTranslation obj) {
		majorTranslDao.delete(obj);
	}

	@Override
	public void deleteById(MajorTranslationId id) {
		majorTranslDao.deleteById(id);
	}

	@Override
	public MajorTranslation findByLocaleAndMajor(Locale locale, Major major) {
		return majorTranslDao.findByLocaleAndMajor(locale, major);
	}
}
