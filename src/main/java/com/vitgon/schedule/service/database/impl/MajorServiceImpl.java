package com.vitgon.schedule.service.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class MajorServiceImpl implements MajorService {
	
	private MajorDao majorDao;
	private MajorTranslationDao majorTranslDao;

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
	public Major findByTitle(String title) {
		MajorTranslation majorTransl = majorTranslDao.findByTitle(title);
		return majorTransl.getMajor();
	}
	
	@Override
	public Major findByUrl(String url) {
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
	public Major findByName(String name) {
		return majorDao.findByName(name);
	}
}
