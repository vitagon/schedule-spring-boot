package com.vitgon.schedule.service.database.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SubjectDao;
import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	private final SubjectDao subjectDao;
	
	@Autowired
	public SubjectServiceImpl(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
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
	public Subject findById(Integer id) {
		return subjectDao.findById(id).orElse(null);
	}

	@Override
	public List<Subject> findAll() {
		return subjectDao.findAll();
	}
	
	@Override
	public Subject findByName(String name) {
		return subjectDao.findByName(name);
	}
}
