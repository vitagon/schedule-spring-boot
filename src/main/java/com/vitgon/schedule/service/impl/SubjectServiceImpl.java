package com.vitgon.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SubjectDao;
import com.vitgon.schedule.dao.translation.SubjectTranslationDao;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private final SubjectDao subjectDao;
	
	@Autowired
	private final SubjectTranslationDao subjectTranslDao;
	
	public SubjectServiceImpl(SubjectDao subjectDao, SubjectTranslationDao subjectTranslDao) {
		this.subjectDao = subjectDao;
		this.subjectTranslDao = subjectTranslDao;
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
		return subjectDao.findById(id).get();
	}

	@Override
	public List<Subject> findAll() {
		return subjectDao.findAll();
	}
	
	@Override
	public Subject findByTitle(String title) {
		SubjectTranslation subjectTransl = subjectTranslDao.findByTitle(title);
		return subjectTransl.getSubjectTranslationId().getSubject();
	}
}
