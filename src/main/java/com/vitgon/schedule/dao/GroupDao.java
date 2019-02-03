package com.vitgon.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Major;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {
	List<Group> findAllByMajorAndCourseNum(Major major, int courseNum);
}
