package com.vitgon.schedule.service.database.impl.translation;

import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;
import com.vitgon.schedule.service.database.translation.MajorTranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MajorTranslationServiceImpl implements MajorTranslationService {

	private final MajorTranslationDao majorTranslDao;

	@Autowired
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
	public Optional<MajorTranslation> findById(MajorTranslationId id) {
		return majorTranslDao.findById(id);
	}

	@Override
	public List<MajorTranslation> findAll() {
		return majorTranslDao.findAll();
	}
	
	@Override
	public void delete(MajorTranslation obj) {
		majorTranslDao.delete(obj);
	}

	@Override
	public void deleteById(MajorTranslationId id) {
		majorTranslDao.deleteById(id);
	}

	@Override
	public Optional<MajorTranslation> findByLocaleIdAndMajorId(Integer localeId, Integer majorId) {
		return majorTranslDao.findByLocaleIdAndMajorId(localeId, majorId);
	}

	@Override
	public void save(Integer majorId, Integer localeId, String translation) {
		majorTranslDao.save(majorId, localeId, translation);
	}

	@Override
	public void deleteByMajorIdAndLocaleId(Integer majorId, Integer localeId) {
		majorTranslDao.deleteByMajorIdAndLocaleId(majorId, localeId);
	}
}
