package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.GroupTranslationDao;
import com.vitgon.schedule.model.database.translation.GroupTranslation;
import com.vitgon.schedule.model.database.translation.pk.GroupTranslationId;
import com.vitgon.schedule.service.database.translation.GroupTranslationService;

@Service
@Transactional
public class GroupTranslationServiceImpl implements GroupTranslationService {
	
	private GroupTranslationDao groupTranslDao;

	public GroupTranslationServiceImpl(GroupTranslationDao groupTranslDao) {
		super();
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
	public Optional<GroupTranslation> findById(GroupTranslationId id) {
		return groupTranslDao.findById(id);
	}

	@Override
	public List<GroupTranslation> findAll() {
		return groupTranslDao.findAll();
	}

	@Override
	public void delete(GroupTranslation obj) {
		groupTranslDao.delete(obj);
	}

	@Override
	public void deleteById(GroupTranslationId id) {
		groupTranslDao.deleteById(id);
	}

	@Override
	public Optional<GroupTranslation> findByLocaleIdAndGroupId(Integer localeId, Integer groupId) {
		return groupTranslDao.findByLocaleIdAndGroupId(localeId, groupId);
	}

	@Override
	public void save(Integer groupId, Integer localeId, String translation) {
		groupTranslDao.save(groupId, localeId, translation);
	}
}
