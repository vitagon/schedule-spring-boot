package com.vitgon.schedule.service.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.dao.translation.SchoolTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.projection.SchoolProjection;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;


@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {
	
	private final SchoolDao schoolDao;
	private final SchoolTranslationDao schoolTranslDao;
	private LocaleConverterService localeConverterService;

	public SchoolServiceImpl(SchoolDao schoolDao, SchoolTranslationDao schoolTranslDao, LocaleConverterService localeConverterService) {
		this.schoolDao = schoolDao;
		this.schoolTranslDao = schoolTranslDao;
		this.localeConverterService = localeConverterService;
	}

	@Override
	public School save(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public School update(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public Optional<School> findById(Integer id) {
		return schoolDao.findById(id);
	}

	@Override
	public List<School> findAll() {
		return schoolDao.findAll();
	}

	@Override
	public School findByTranslation(String translation) {
		SchoolTranslation schoolTransl = schoolTranslDao.findByTranslation(translation);
		return schoolTransl.getSchoolTranslationId().getSchool();
	}

	@Override
	public List<School> findAllByLocale(Locale locale) {
		return schoolDao.findAllByLocale(locale);
	}
	
	@Override
	public void delete(School obj) {
		schoolDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		schoolDao.deleteById(id);
	}
	
	@Override
	public School findByName(String name) {
		return schoolDao.findByName(name);
	}

	@Override
	public List<SchoolProjection> getAllJoiningWithMajors() {
		Locale locale = localeConverterService.getClientLocale();
		return schoolDao.getAllJoiningWithMajors(locale.getId());
	}

	@Override
	public List<SchoolProjection> getAllJoiningWithMajors(Integer localeId) {
		return schoolDao.getAllJoiningWithMajors(localeId);
	}
}
