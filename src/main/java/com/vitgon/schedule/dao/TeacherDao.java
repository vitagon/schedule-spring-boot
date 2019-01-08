package com.vitgon.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Teacher;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
}
