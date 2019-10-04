package com.vitgon.schedule.dao;

import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.projection.SubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao extends JpaRepository<Subject, Integer> {
	Subject findByName(String name);
	
	@Query(value = 
			"SELECT "
			+	"s.id, s.name, st.translation " +
			"FROM "
			+	"subjects s " +
			"LEFT JOIN "
			+ 	"(select * from subject_translations where locale_id = ?1) st ON s.id = st.subject_id",
			nativeQuery = true)
	public List<SubjectProjection> findAllByLocaleId(Integer localeId);
}
