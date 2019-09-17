package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.LocaleDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocaleServiceImpl implements LocaleService {

	private final LocaleDao localeDao;

	@Autowired
	public LocaleServiceImpl(LocaleDao localeDao) {
		this.localeDao = localeDao;
	}
	
	@Override
	public Locale save(Locale obj) {
		return localeDao.save(obj);
	}

	@Override
	public Locale update(Locale obj) {
		return localeDao.save(obj);
	}

	@Override
	public Optional<Locale> findById(Integer id) {
		return localeDao.findById(id);
	}

	@Override
	public List<Locale> findAll() {
		return localeDao.findAll();
	}

	@Override
	public Locale findByCode(String code) {
		return localeDao.findByCode(code);
	}
	
	@Override
	public void delete(Locale obj) {
		localeDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		localeDao.deleteById(id);
	}
}
