package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.service.database.base.Service;

public interface SubjectService extends Service<Subject, Integer> {
	Subject findByName(String name);
}
