package com.vitgon.schedule.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.vitgon.schedule.model.Teacher;
import com.vitgon.schedule.service.base.Service;

public interface TeacherService extends Service<Teacher, Integer> {
	List<Teacher> findAll(Specification spec);
}
