package com.vitgon.schedule.service.database.impl;

import com.vitgon.schedule.dao.ScheduleDao;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.projection.ScheduleProjection;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	
	private final ScheduleDao scheduleDao;
	private LocaleConverterService localeConverterService;

	public ScheduleServiceImpl(ScheduleDao scheduleDao, LocaleConverterService localeConverterService) {
		this.scheduleDao = scheduleDao;
		this.localeConverterService = localeConverterService;
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
	public Optional<Schedule> findById(Integer id) {
		return scheduleDao.findById(id);
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
	public void delete(Schedule obj) {
		scheduleDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		scheduleDao.deleteById(id);
	}

	@Override
	public List<ScheduleProjection> findByGroupId(Integer groupId) {
		Integer localeId = localeConverterService.getClientLocale().getId();
		return scheduleDao.findByGroupId(groupId, localeId);
	}

	@Override
	public ScheduleProjection findByGroupIdAndDayNumAndWeekAndLessonNum(
			Integer groupId, Integer dayNum, String week, Integer lessonNum) {
		return scheduleDao.getByGroupIdAndDayNumAndWeekAndLessonNum(groupId, dayNum, week, lessonNum);
	}

	@Override
	public ScheduleProjection getById(Integer id, Integer localeId) {
		return scheduleDao.getById(id, localeId);
	}
}
