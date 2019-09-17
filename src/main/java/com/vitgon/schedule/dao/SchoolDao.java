package com.vitgon.schedule.dao;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.projection.SchoolProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao extends JpaRepository<School, Integer> {
	
	@Query("SELECT s from School s INNER JOIN s.translations st WHERE st.schoolTranslationId.locale = :locale")
	List<School> findAllByLocale(@Param("locale") Locale locale);
	
	School findByName(String name);
	School findByUrl(String url);
	
	@Query(value =
			"SELECT "
			+	"s.id, s.name, s.url, st.translation, "
			+ 	"m.id AS major_id, m.name AS major_name, m.url AS major_url, mt.translation AS major_translation " +
			"FROM "
			+	"schools s " +
			"LEFT JOIN "
			+	"(select * from school_translations where locale_id = ?1) st ON s.id = st.school_id " +
			"LEFT JOIN "
			+	"majors m ON s.id = m.school_id " +
			"LEFT JOIN "
			+ 	"(select * from major_translations where locale_id = ?1) mt ON m.id = mt.major_id",
			nativeQuery = true)
	List<SchoolProjection> getAllJoiningWithMajors(Integer localeId);
}
