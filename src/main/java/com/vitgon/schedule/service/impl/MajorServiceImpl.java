package com.vitgon.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.model.translation.MajorTranslation;
import com.vitgon.schedule.service.MajorService;

@Service
@Transactional
public class MajorServiceImpl implements MajorService {

	@Autowired
	private final MajorDao majorDao;
	
	@Autowired
	private final MajorTranslationDao majorTranslDao;
	
	public MajorServiceImpl(MajorDao majorDao, MajorTranslationDao majorTranslDao) {
		this.majorDao = majorDao;
		this.majorTranslDao = majorTranslDao;
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
	public Major findById(Integer id) {
		return majorDao.findById(id).get();
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
}
