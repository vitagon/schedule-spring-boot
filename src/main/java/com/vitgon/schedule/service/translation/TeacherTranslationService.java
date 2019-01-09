package com.vitgon.schedule.service.translation;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.vitgon.schedule.model.translation.TeacherTranslation;
import com.vitgon.schedule.model.translation.pk.TeacherTranslationId;
import com.vitgon.schedule.service.base.Service;

public interface TeacherTranslationService extends Service<TeacherTranslation, TeacherTranslationId> {
}
