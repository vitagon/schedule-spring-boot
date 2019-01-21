package com.vitgon.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Subject;

@Repository
public interface SubjectDao extends JpaRepository<Subject, Integer> {
	Subject findByName(String name);
}
