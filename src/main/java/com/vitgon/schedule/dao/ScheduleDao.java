package com.vitgon.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
	List<Schedule> findByGroup(Group group);
	Schedule findByGroupAndDayNumAndWeekAndLessonNum(Group group, int dayNum, String week, int lessonNum);
}
