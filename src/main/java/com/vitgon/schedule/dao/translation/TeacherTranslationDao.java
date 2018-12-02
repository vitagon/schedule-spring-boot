package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.translation.TeacherTranslation;
import com.vitgon.schedule.model.translation.pk.TeacherTranslationId;

@Repository
public interface TeacherTranslationDao extends JpaRepository<TeacherTranslation, TeacherTranslationId>{

}
