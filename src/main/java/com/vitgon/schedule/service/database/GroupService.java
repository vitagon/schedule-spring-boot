package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.database.base.Service;

import java.util.List;

public interface GroupService extends Service<Group, Integer> {
	List<GroupProjection> getAllByMajorIdAndCourseNum(Integer majorId, Integer courseNum);
	List<GroupProjection> getAllByMajorUrlAndLocaleId(String url, Integer localeId);
	List<GroupProjection> getAllByLocaleId(Integer localeId);

	List<GroupProjection> getAllByMajorIdAndLocaleId(Integer majorId, Integer localeId);
	GroupProjection getByGroupIdAndLocaleId(Integer groupId, Integer localeId);
}
