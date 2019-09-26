package com.vitgon.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.projection.GroupProjection;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {
	
	@Query(value = 
			"SELECT "
			+	"g.id, g.number, g.suffix, g.course_num, gt.suffix_translation, m.degree, g.major_id " +
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
	
	@Query(value = 
			"SELECT "
			+	"g.id, g.number, g.suffix, g.course_num, gt.suffix_translation, g.major_id, "
			+	"m.degree " +
			"FROM "
			+	"_groups g " +
			"JOIN "
			+	"(select * from group_translations where locale_id = :localeId) gt ON gt.group_id = g.id " +
			"JOIN "
			+	"majors m ON m.id = g.major_id " +
			"WHERE "
			+	"g.major_id = (select id from majors where url = :url) ",
			nativeQuery = true)
	List<GroupProjection> getAllByMajorUrlAndLocaleId(@Param("url") String url, @Param("localeId") Integer localeId);
	
	@Query(value = 
			"SELECT "
			+	"g.id, g.number, g.suffix, g.course_num, gt.suffix_translation, "
			+	"m.degree " +
			"FROM "
			+	"_groups g " +
			"JOIN "
			+	"(select * from group_translations where locale_id = :localeId) gt ON gt.group_id = g.id " +
			"JOIN "
			+	"majors m ON m.id = g.major_id ",
			nativeQuery = true)
	List<GroupProjection> getAllByLocaleId(@Param("localeId") Integer localeId);

	@Query(value =
			"SELECT "
			+ 	"g.id, g.number, g.suffix, g.course_num, m.degree, g.major_id, "
			+	"gt.suffix_translation " +
			"FROM "
			+	"_groups g " +
			"JOIN "
			+	"majors m ON m.id = g.major_id " +
			"LEFT JOIN "
			+	"(select * from group_translations where locale_id = :localeId) gt ON gt.group_id = g.id " +
			"WHERE "
			+	"g.major_id = :majorId",
			nativeQuery = true)
    List<GroupProjection> getAllByMajorIdAndLocaleId(@Param("majorId") Integer majorId, @Param("localeId") Integer localeId);

	@Query(value =
			"SELECT "
			+ 	"g.id, g.number, g.suffix, g.course_num, m.degree, g.major_id, "
			+	"gt.suffix_translation " +
			"FROM "
			+	"_groups g " +
			"JOIN "
			+	"majors m ON m.id = g.major_id " +
			"LEFT JOIN "
			+	"(select * from group_translations where locale_id = :localeId) gt ON gt.group_id = g.id " +
			"WHERE "
			+	"g.id = :groupId",
			nativeQuery = true)
	GroupProjection getByGroupIdAndLocaleId(@Param("groupId") Integer groupId, @Param("localeId") Integer localeId);
}
