package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.SubjectTranslationDao;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.model.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@Service
@Transactional
public class SubjectTranslationServiceImpl implements SubjectTranslationService {

	@Autowired
	private final SubjectTranslationDao subjectTranslDao;
	
	public SubjectTranslationServiceImpl(SubjectTranslationDao subjectTranslDao) {
		this.subjectTranslDao = subjectTranslDao;
	}
	
	@Override
	public SubjectTranslation save(SubjectTranslation obj) {
		return subjectTranslDao.save(obj);
	}

	@Override
	public SubjectTranslation update(SubjectTranslation obj) {
		return subjectTranslDao.save(obj);
	}

	@Override
	public SubjectTranslation findById(SubjectTranslationId id) {
		return subjectTranslDao.findById(id).get();
	}

	@Override
	public List<SubjectTranslation> findAll() {
		return subjectTranslDao.findAll();
	}
}
