package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.SchoolTranslationDao;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;
import com.vitgon.schedule.service.database.translation.SchoolTranslationService;

@Service
@Transactional
public class SchoolTranslationServiceImpl implements SchoolTranslationService {

	@Autowired
	private final SchoolTranslationDao schoolTranslDao;
	
	public SchoolTranslationServiceImpl(SchoolTranslationDao schoolTranslRep) {
		this.schoolTranslDao = schoolTranslRep;
	}
	
	@Override
	public SchoolTranslation save(SchoolTranslation obj) {
		return schoolTranslDao.save(obj);
	}

	@Override
	public SchoolTranslation update(SchoolTranslation obj) {
		return schoolTranslDao.save(obj);
	}

	@Override
	public SchoolTranslation findById(SchoolTranslationId id) {
		return schoolTranslDao.findById(id).get();
	}

	@Override
	public List<SchoolTranslation> findAll() {
		return schoolTranslDao.findAll();
	}
}
