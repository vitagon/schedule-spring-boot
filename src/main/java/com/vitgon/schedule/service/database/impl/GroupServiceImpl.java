package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.GroupDao;
import com.vitgon.schedule.dao.translation.GroupTranslationDao;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.model.translation.GroupTranslation;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	
	private final GroupDao groupDao;
	private MajorService majorService;
	private final GroupTranslationDao groupTranslDao;
	
	@Autowired
	public GroupServiceImpl(GroupDao groupDao,
							MajorService majorService,
							GroupTranslationDao groupTranslDao) {
		this.groupDao = groupDao;
		this.majorService = majorService;
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
		return groupDao.findById(id).orElse(null);
	}

	@Override
	public List<Group> findAll() {
		return groupDao.findAll();
	}

	@Override
	public List<Group> findAllByMajorAndCourseNum(int majorId, int courseNum) {
		Major major = majorService.findById(majorId);
		if (major == null) {
			return null;
		}
		return groupDao.findAllByMajorAndCourseNum(major, courseNum);
	}
}
