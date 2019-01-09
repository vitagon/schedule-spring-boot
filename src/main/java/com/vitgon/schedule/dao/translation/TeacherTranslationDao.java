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
}
