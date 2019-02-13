package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.base.Service;

public interface SubjectService extends Service<Subject, Integer> {
	Subject findByName(String name);
}
