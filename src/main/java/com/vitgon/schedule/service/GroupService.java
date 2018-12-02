package com.vitgon.schedule.service;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.service.base.Service;

public interface GroupService extends Service<Group, Integer> {
	Group findByTitle(String title);
}
