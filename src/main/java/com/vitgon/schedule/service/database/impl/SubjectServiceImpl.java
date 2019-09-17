package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.SubjectDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.projection.SubjectProjection;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	private final SubjectDao subjectDao;
	private LocaleConverterService localeConverterService;

	public SubjectServiceImpl(SubjectDao subjectDao, LocaleConverterService localeConverterService) {
		this.subjectDao = subjectDao;
		this.localeConverterService = localeConverterService;
	}

	@Override
	public Subject save(Subject obj) {
		return subjectDao.save(obj);
	}

	@Override
	public Subject update(Subject obj) {
		return subjectDao.save(obj);
	}

	@Override
	public Optional<Subject> findById(Integer id) {
		return subjectDao.findById(id);
	}

	@Override
	public List<Subject> findAll() {
		return subjectDao.findAll();
	}
	
	@Override
	public Subject findByName(String name) {
		return subjectDao.findByName(name);
	}
	
	@Override
	public void delete(Subject obj) {
		subjectDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		subjectDao.deleteById(id);
	}

	@Override
	public List<SubjectProjection> findAllByBrowserDefaultLocale() {
		Locale locale = localeConverterService.getClientLocale();
		return findAllByLocaleId(locale.getId());
	}

	@Override
	public List<SubjectProjection> findAllByLocaleId(Integer localeId) {
		return subjectDao.findAllByLocaleId(localeId);
	}
}
