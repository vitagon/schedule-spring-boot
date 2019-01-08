package com.vitgon.schedule.dao.translation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.translation.TeacherTranslation;
import com.vitgon.schedule.model.translation.pk.TeacherTranslationId;

@Repository
public interface TeacherTranslationDao extends JpaRepository<TeacherTranslation, TeacherTranslationId> {
	
	@Query(value = "SELECT * FROM teacher_translations WHERE firstname LIKE %:keyword% OR lastname LIKE %:keyword% OR middlename LIKE %:keyword%",
			nativeQuery = true)
	public List<TeacherTranslation> searchTeachers(@Param("keyword") String keyword);
}
