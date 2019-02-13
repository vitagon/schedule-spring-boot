package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.pojo.CourseNumPOJO;
import com.vitgon.schedule.pojo.GroupPOJO;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

public class GroupUtil {
	public static String getGroupTitle(Group group, Locale locale) {
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return group.getName();
		}
		
		return group.getTranslations().stream()
				.filter(x -> locale == x.getLocale())
				.map(x -> x.getTitle())
				.findFirst().get();
	}
	
	public static Map<CourseNumPOJO, List<GroupPOJO>> prepareGroupPojos(List<Group> groups, Locale locale) {
		Map<CourseNumPOJO, List<GroupPOJO>> groupsMap = new HashMap<>();
		
		for (Group group : groups) {
			CourseNumPOJO courseNum = new CourseNumPOJO(group.getCourseNum());
			
			if (!groupsMap.containsKey(courseNum)) {
				groupsMap.put(courseNum, new ArrayList<>());
			}
			
			List<GroupPOJO> groupsList = groupsMap.get(courseNum);
			groupsList.add(new GroupPOJO(
					group.getId(),
					getGroupTitle(group, locale)
			));
		}
		
		return sortGroupsMap(groupsMap);
	}
	
	public static Map<CourseNumPOJO, List<GroupPOJO>> sortGroupsMap(Map<CourseNumPOJO, List<GroupPOJO>> unsortedGroupsMap) {
		List<Entry<CourseNumPOJO, List<GroupPOJO>>> list = new LinkedList<Map.Entry<CourseNumPOJO, List<GroupPOJO>>>(unsortedGroupsMap.entrySet());
		
		Collections.sort(list, new Comparator<Entry<CourseNumPOJO, List<GroupPOJO>>>() {
			public int compare(Map.Entry<CourseNumPOJO, List<GroupPOJO>> o1, Map.Entry<CourseNumPOJO, List<GroupPOJO>> o2) {
				return (o1.getKey()).compareTo(o2.getKey());
			}
		});

		//convert sortedMap back to Map
		Map<CourseNumPOJO, List<GroupPOJO>> sortedMap = new LinkedHashMap<CourseNumPOJO, List<GroupPOJO>>();
		for (Entry<CourseNumPOJO, List<GroupPOJO>> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public static String transliterateTo(String groupTitle) {
		return null;
	}
}
