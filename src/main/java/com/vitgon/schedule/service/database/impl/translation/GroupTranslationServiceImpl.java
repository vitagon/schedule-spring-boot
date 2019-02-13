package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.GroupTranslationDao;
import com.vitgon.schedule.model.database.translation.GroupTranslation;
import com.vitgon.schedule.model.database.translation.pk.GroupTranslationId;
import com.vitgon.schedule.service.database.translation.GroupTranslationService;

@Service
@Transactional
public class GroupTranslationServiceImpl implements GroupTranslationService {

	@Autowired
	private final GroupTranslationDao groupTranslDao;
	
	public GroupTranslationServiceImpl(GroupTranslationDao groupTranslDao) {
		this.groupTranslDao = groupTranslDao;
	}
	
	@Override
	public GroupTranslation save(GroupTranslation obj) {
		return groupTranslDao.save(obj);
	}

	@Override
	public GroupTranslation update(GroupTranslation obj) {
		return groupTranslDao.save(obj);
	}

	@Override
	public GroupTranslation findById(GroupTranslationId id) {
		return groupTranslDao.findById(id).get();
	}

	@Override
	public List<GroupTranslation> findAll() {
		return groupTranslDao.findAll();
	}
}
