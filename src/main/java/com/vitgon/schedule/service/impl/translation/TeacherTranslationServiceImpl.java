package com.vitgon.schedule.service.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.TeacherTranslationDao;
import com.vitgon.schedule.model.translation.TeacherTranslation;
import com.vitgon.schedule.model.translation.pk.TeacherTranslationId;
import com.vitgon.schedule.service.translation.TeacherTranslationService;

@Service
@Transactional
public class TeacherTranslationServiceImpl implements TeacherTranslationService {

	@Autowired
	private final TeacherTranslationDao teacherTranslDao;
	
	public TeacherTranslationServiceImpl(TeacherTranslationDao teacherTranslDao) {
		this.teacherTranslDao = teacherTranslDao;
	}
	
	@Override
	public TeacherTranslation save(TeacherTranslation obj) {
		return teacherTranslDao.save(obj);
	}

	@Override
	public TeacherTranslation update(TeacherTranslation obj) {
		return teacherTranslDao.save(obj);
	}

	@Override
	public TeacherTranslation findById(TeacherTranslationId id) {
		return teacherTranslDao.findById(id).get();
	}

	@Override
	public List<TeacherTranslation> findAll() {
		return teacherTranslDao.findAll();
	}

	@Override
	public List<TeacherTranslation> searchTeachers(String keyword) {
		return teacherTranslDao.searchTeachers(keyword);
	}
}
