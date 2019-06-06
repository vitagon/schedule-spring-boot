package com.vitgon.schedule.service.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SubjectDao;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

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
}
