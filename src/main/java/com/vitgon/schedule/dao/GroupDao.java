package com.vitgon.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.projection.GroupProjection;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {
	
	@Query(value = 
			"SELECT "
			+	"g.id, g.number, g.suffix, g.course_num, gt.suffix_translation, m.degree " +
			"FROM "
			+	"_groups g " +
			"LEFT JOIN "
			+	"(select * from group_translations where locale_id = ?3) gt ON g.id = gt.group_id " +
			"JOIN "
			+ 	"majors m ON m.id = g.major_id " +
			"WHERE "
			+ 	"g.major_id = ?1 AND g.course_num = ?2 ",
			nativeQuery = true)
	List<GroupProjection> findAllByMajorIdAndCourseNum(Integer majorId, Integer courseNum, Integer localeId);
}
