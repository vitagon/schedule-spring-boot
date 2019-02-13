package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.service.database.base.Service;

public interface ScheduleService extends Service<Schedule, Integer>{
	List<Schedule> findByGroup(Group group);
	Schedule findByGroupAndDayNumAndWeekAndLessonNum(Group group, int dayNum, String week, int lessonNum);
}
