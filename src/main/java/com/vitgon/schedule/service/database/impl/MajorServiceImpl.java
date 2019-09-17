package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.projection.MajorProjection;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MajorServiceImpl implements MajorService {
	
	private MajorDao majorDao;
	private MajorTranslationDao majorTranslDao;
	private LocaleConverterService localeConverterService;

	public MajorServiceImpl(MajorDao majorDao, MajorTranslationDao majorTranslDao, LocaleConverterService localeConverterService) {
		this.majorDao = majorDao;
		this.majorTranslDao = majorTranslDao;
		this.localeConverterService = localeConverterService;
	}

	@Override
	public Major save(Major obj) {
		return majorDao.save(obj);
	}

	@Override
	public Major update(Major obj) {
		return majorDao.save(obj);
	}

	@Override
	public Optional<Major> findById(Integer id) {
		return majorDao.findById(id);
	}

	@Override
	public List<Major> findAll() {
		return majorDao.findAll();
	}

	@Override
	public Major findByTranslation(String translation) {
		MajorTranslation majorTransl = majorTranslDao.findByTranslation(translation);
		return majorTransl.getMajor();
	}
	
	@Override
	public Optional<Major> findByUrl(String url) {
		return majorDao.findByUrl(url);
	}
	
	@Override
	public void delete(Major obj) {
		majorDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		majorDao.deleteById(id);
	}

	@Override
	public Optional<Major> findByName(String name) {
		return majorDao.findByName(name);
	}

	@Override
	public List<MajorProjection> findBySchoolIdAndLocaleId(Integer schoolId, Integer localeId) {
		return majorDao.getAllBySchoolIdAndLocaleId(schoolId, localeId);
	}

	@Override
	public List<MajorProjection> findBySchoolIdAndBrowserDefaultLanguage(Integer schoolId) {
		Locale locale = localeConverterService.getClientLocale();
		return findBySchoolIdAndLocaleId(schoolId, locale.getId());
	}
	
	@Override
	public List<MajorProjection> getAllLeftJoiningOnLocaleId(Integer localeId) {
		return majorDao.getAllLeftJoiningOnLocaleId(localeId);
	}
}
