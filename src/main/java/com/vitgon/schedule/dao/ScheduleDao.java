package com.vitgon.schedule.dao;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.projection.ScheduleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
	List<Schedule> findByGroup(Group group);
	Schedule findByGroupAndDayNumAndWeekAndLessonNum(Group group, int dayNum, String week, int lessonNum);
	
	@Query(value = 
			"SELECT "
			+	"s.id, s.day_num, s.week_type, s.lesson_num, s.lesson_type AS lesson_type_id, s.classroom, s.group_id, "
			+	"st.subject_id, st.translation AS subject_name, "
			+	"u.id AS user_id, u.lastname, u.firstname, u.middlename, "
			+	"ltime.lesson_time, "
			+ 	"ltypes.lesson_type AS lesson_type_name " +
			"FROM "
			+ 	"schedules s " +
			"LEFT JOIN "
			+	"users u ON s.user_id = u.id " +
			"LEFT JOIN "
			+	"(select * from subject_translations where locale_id = ?2) st ON s.subject_id = st.subject_id " +
			"JOIN "
			+	"lessons_time ltime ON s.lesson_num = ltime.lesson_num " +
			"LEFT JOIN "
			+	"lesson_types ltypes ON s.lesson_type = ltypes.id " +
			"WHERE "
			+	" s.group_id = ?1",
			nativeQuery = true)
	List<ScheduleProjection> findByGroupId(Integer groupId, Integer localeId);
	
	@Query(value = 
			"SELECT "
			+	"s.id, s.day_num, s.week_type, s.lesson_num, s.lesson_type AS lesson_type_id, s.classroom, s.group_id, "
			+	"st.subject_id, st.translation AS subject_name, "
			+	"u.id AS user_id, u.lastname, u.firstname, u.middlename, "
			+	"ltime.lesson_time, "
			+ 	"ltypes.lesson_type AS lesson_type_name " +
			"FROM "
			+ 	"schedules s " +
			"LEFT JOIN "
			+	"users u ON s.user_id = u.id " +
			"LEFT JOIN "
			+	"(select * from subject_translations where locale_id = ?2) st ON s.subject_id = st.subject_id " +
			"JOIN "
			+	"lessons_time ltime ON s.lesson_num = ltime.lesson_num " +
			"LEFT JOIN "
			+	"lesson_types ltypes ON s.lesson_type = ltypes.id " +
			"WHERE "
			+	" s.group_id = ?1 AND s.day_num = ?2 AND "
			+ 	" s.week_type = ?3 AND s.lesson_num = ?4",
			nativeQuery = true)
	ScheduleProjection getByGroupIdAndDayNumAndWeekAndLessonNum(Integer groupId, Integer dayNum, String week, Integer lessonNum);
	
	@Query(value = 
			"SELECT "
			+	"s.id, s.day_num, s.week_type, s.lesson_num, s.lesson_type AS lesson_type_id, s.classroom, s.group_id, "
			+	"st.subject_id, st.translation AS subject_name, "
			+	"u.id AS user_id, u.lastname, u.firstname, u.middlename, "
			+	"ltime.lesson_time, "
			+ 	"ltypes.lesson_type AS lesson_type_name " +
			"FROM "
			+ 	"schedules s " +
			"LEFT JOIN "
			+	"users u ON s.user_id = u.id " +
			"LEFT JOIN "
			+	"(select * from subject_translations where locale_id = ?2) st ON s.subject_id = st.subject_id " +
			"JOIN "
			+	"lessons_time ltime ON s.lesson_num = ltime.lesson_num " +
			"LEFT JOIN "
			+	"lesson_types ltypes ON s.lesson_type = ltypes.id " +
			"WHERE "
			+	" s.id = ?1",
			nativeQuery = true)
	ScheduleProjection getById(Integer id, Integer localeId);
}
