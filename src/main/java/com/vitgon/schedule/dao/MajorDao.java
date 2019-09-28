package com.vitgon.schedule.dao;

import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.projection.MajorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajorDao extends JpaRepository<Major, Integer> {
	
	Optional<Major> findByName(String name);
	
	@Query(value =
			"SELECT "
			+	"m.id, m.name, m.url, m.duration, m.degree, mt.translation, "
			+	"s.id AS schoolId, s.name AS schoolName " +
			"FROM "
			+ 	"majors m " +
			"JOIN "
			+	"schools s ON s.id = m.school_id " +
			"LEFT JOIN "
			+ 	"(select * from major_translations where locale_id = ?2) mt ON m.id = mt.major_id " +
			"WHERE "
			+	"m.school_id = ?1",
			nativeQuery = true)
	List<MajorProjection> getAllBySchoolIdAndLocaleId(Integer schoolId, Integer localeId);
	
	@Query(value =
			"SELECT "
			+	"m.id, m.name, m.url, m.duration, m.degree, mt.translation, "
			+	"s.id AS schoolId, s.name AS schoolName " +
			"FROM "
			+ 	"majors m " +
			"JOIN "
			+	"schools s ON s.id = m.school_id " +
			"LEFT JOIN "
			+ 	"(select * from major_translations where locale_id = ?1) mt ON m.id = mt.major_id ",
			nativeQuery = true)
	List<MajorProjection> getAllLeftJoiningOnLocaleId(Integer localeId);

	Optional<Major> findByUrl(String url);

	@Query(value =
			"SELECT "
			+	"m.id, m.name, m.url, m.duration, m.degree, mt.translation, "
			+	"s.id AS schoolId, s.name AS schoolName " +
			"FROM "
			+ 	"majors m " +
			"JOIN "
			+	"schools s ON s.id = m.school_id " +
			"LEFT JOIN "
			+ 	"(select * from major_translations where locale_id = ?1) mt ON m.id = mt.major_id " +
			"WHERE "
			+	"m.id = ?2",
			nativeQuery = true)
    Optional<MajorProjection> getByLocaleIdAndMajorId(Integer localeId, Integer majorId);
}
