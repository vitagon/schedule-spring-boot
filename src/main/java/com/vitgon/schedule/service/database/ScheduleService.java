package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.projection.ScheduleProjection;
import com.vitgon.schedule.service.database.base.Service;

public interface ScheduleService extends Service<Schedule, Integer>{
	List<Schedule> findByGroup(Group group);
	List<ScheduleProjection> findByGroupId(Integer groupId);
	ScheduleProjection findByGroupIdAndDayNumAndWeekAndLessonNum(Integer groupId, Integer dayNum, String week, Integer lessonNum);
	ScheduleProjection getById(Integer id, Integer localeId);
}
