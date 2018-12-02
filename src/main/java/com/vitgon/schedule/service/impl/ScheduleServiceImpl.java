package com.vitgon.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.ScheduleDao;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.service.ScheduleService;

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
		return scheduleDao.findById(id).get();
	}

	@Override
	public List<Schedule> findAll() {
		return scheduleDao.findAll();
	}
}
