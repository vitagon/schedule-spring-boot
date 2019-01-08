package com.vitgon.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.TeacherDao;
import com.vitgon.schedule.model.Teacher;
import com.vitgon.schedule.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private final TeacherDao teacherDao;
	
	public TeacherServiceImpl(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public Teacher save(Teacher obj) {
		return teacherDao.save(obj);
	}

	@Override
	public Teacher update(Teacher obj) {
		return teacherDao.save(obj);
	}

	@Override
	public Teacher findById(Integer id) {
		return teacherDao.findById(id).get();
	}

	@Override
	public List<Teacher> findAll() {
		return teacherDao.findAll();
	}
	
	@Override
	public List<Teacher> findAll(Specification spec) {
		return teacherDao.findAll(spec);
	}
}
