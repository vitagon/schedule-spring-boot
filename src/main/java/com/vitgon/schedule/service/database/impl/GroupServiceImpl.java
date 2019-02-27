package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.GroupDao;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	
	private final GroupDao groupDao;
	private MajorService majorService;

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
		if (major == null || courseNum == 0) {
			return null;
		}
		return groupDao.findAllByMajorAndCourseNum(major, courseNum);
	}
	
	@Override
	public void delete(Group obj) {
		groupDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		groupDao.deleteById(id);
	}
}
