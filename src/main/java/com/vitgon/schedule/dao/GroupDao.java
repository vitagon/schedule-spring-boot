package com.vitgon.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Group;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {
}
