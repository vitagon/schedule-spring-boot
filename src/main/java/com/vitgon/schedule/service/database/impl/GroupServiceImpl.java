package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.GroupDao;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	
	private final GroupDao groupDao;
	private LocaleConverterService localeConverterService;

	public GroupServiceImpl(GroupDao groupDao, LocaleConverterService localeConverterService) {
		this.groupDao = groupDao;
		this.localeConverterService = localeConverterService;
	}

	@Override
	public Group save(Group obj) {
		return groupDao.save(obj);
	}

	@Override
	public Group update(Group obj) {
		return groupDao.save(obj);
	}

	@Override
	public Optional<Group> findById(Integer id) {
		return groupDao.findById(id);
	}

	@Override
	public List<Group> findAll() {
		return groupDao.findAll();
	}

	@Override
	public List<GroupProjection> getAllByMajorIdAndCourseNum(Integer majorId, Integer courseNum) {
		Locale locale = localeConverterService.getClientLocale();
		return groupDao.findAllByMajorIdAndCourseNum(majorId, courseNum, locale.getId());
	}
	
	@Override
	public void delete(Group obj) {
		groupDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		groupDao.deleteById(id);
	}

	@Override
	public List<GroupProjection> getAllByMajorUrlAndLocaleId(String url, Integer localeId) {
		return groupDao.getAllByMajorUrlAndLocaleId(url, localeId);
	}

	@Override
	public List<GroupProjection> getAllByLocaleId(Integer localeId) {
		return groupDao.getAllByLocaleId(localeId);
	}

	@Override
	public List<GroupProjection> getAllByMajorIdAndLocaleId(Integer majorId, Integer localeId) {
		return groupDao.getAllByMajorIdAndLocaleId(majorId, localeId);
	}

	@Override
	public GroupProjection getByGroupIdAndLocaleId(Integer groupId, Integer localeId) {
		return groupDao.getByGroupIdAndLocaleId(groupId, localeId);
	}
}
