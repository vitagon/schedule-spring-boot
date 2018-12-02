package com.vitgon.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.GroupDao;
import com.vitgon.schedule.dao.translation.GroupTranslationDao;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.translation.GroupTranslation;
import com.vitgon.schedule.service.GroupService;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private final GroupDao groupDao;
	
	@Autowired
	private final GroupTranslationDao groupTranslDao;
	
	public GroupServiceImpl(GroupDao groupDao, GroupTranslationDao groupTranslDao) {
		this.groupDao = groupDao;
		this.groupTranslDao = groupTranslDao;
	}
	
	@Override
	public Group findByTitle(String title) {
		GroupTranslation groupTransl = groupTranslDao.findByTitle(title);
		return groupTransl.getGroup();
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
	public Group findById(Integer id) {
		return groupDao.findById(id).get();
	}

	@Override
	public List<Group> findAll() {
		return groupDao.findAll();
	}
}
