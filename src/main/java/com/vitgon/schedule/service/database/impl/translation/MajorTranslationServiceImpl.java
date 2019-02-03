package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.translation.MajorTranslation;
import com.vitgon.schedule.model.translation.pk.MajorTranslationId;
import com.vitgon.schedule.service.database.translation.MajorTranslationService;

@Service
@Transactional
public class MajorTranslationServiceImpl implements MajorTranslationService {

	@Autowired
	private final MajorTranslationDao majorTranslDao;
	
	public MajorTranslationServiceImpl(MajorTranslationDao majorTranslDao) {
		this.majorTranslDao = majorTranslDao;
	}
	
	@Override
	public MajorTranslation save(MajorTranslation obj) {
		return majorTranslDao.save(obj);
	}

	@Override
	public MajorTranslation update(MajorTranslation obj) {
		return majorTranslDao.save(obj);
	}

	@Override
	public MajorTranslation findById(MajorTranslationId id) {
		return majorTranslDao.findById(id).get();
	}

	@Override
	public List<MajorTranslation> findAll() {
		return majorTranslDao.findAll();
	}
}
