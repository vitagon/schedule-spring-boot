package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.service.database.base.Service;

public interface SubjectService extends Service<Subject, Integer> {
	Subject findByName(String name);
	Subject findByTitle(String title);
}
