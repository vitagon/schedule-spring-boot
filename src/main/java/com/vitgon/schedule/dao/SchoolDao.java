package com.vitgon.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.School;

@Repository
public interface SchoolDao extends JpaRepository<School, Integer> {
}
