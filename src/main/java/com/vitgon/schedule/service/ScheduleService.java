package com.vitgon.schedule.service;

import java.util.List;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.service.base.Service;

public interface ScheduleService extends Service<Schedule, Integer>{
	List<Schedule> findByGroup(Group group);
}
