package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.ScheduleDao;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.service.database.ScheduleService;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private final ScheduleDao scheduleDao;
	
	public ScheduleServiceImpl(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}
	
	@Override
	public Schedule save(Schedule obj) {
		return scheduleDao.save(obj);
	}

	@Override
	public Schedule update(Schedule obj) {
		return scheduleDao.save(obj);
	}

	@Override
	public Schedule findById(Integer id) {
		return scheduleDao.findById(id).orElse(null);
	}

	@Override
	public List<Schedule> findAll() {
		return scheduleDao.findAll();
	}

	@Override
	public List<Schedule> findByGroup(Group group) {
		return scheduleDao.findByGroup(group);
	}
	
	@Override
	public Schedule findByGroupAndDayNumAndWeekAndLessonNum(Group group, int dayNum, String week, int lessonNum) {
		return scheduleDao.findByGroupAndDayNumAndWeekAndLessonNum(group, dayNum, week, lessonNum);
	}

	@Override
	public void delete(Schedule obj) {
		scheduleDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		scheduleDao.deleteById(id);
	}
}
