package com.vitgon.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Schedule;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
	List<Schedule> findByGroup(Group group);
}
