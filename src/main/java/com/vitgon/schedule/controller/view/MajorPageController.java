package com.vitgon.schedule.controller.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.model.translation.GroupTranslation;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.MajorService;

@Controller
public class MajorPageController {
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private LocaleService localeService;
	
	@RequestMapping("/school/{school}/major/{major}")
	public String showMajorGroups(@PathVariable("major") String majorUrl, HttpServletRequest request, Model model) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		
		Major major = majorService.findByUrl(majorUrl);
		Map<Integer, Map<String,Object>> coursesMap = new HashMap<>();
		
		List<Group> groups = major.getGroups();
		
		for (Group group : groups) {
			int courseNum = group.getCourseNum();
			
			if (!coursesMap.containsKey(courseNum)) {
				coursesMap.put(courseNum, new HashMap<>());
			}
			
			Map<String,Object> groupMap = coursesMap.get(courseNum);
			groupMap.put("title", group.getGroupTranslations().stream()
					.filter(x -> locale == x.getLocale())
					.map(GroupTranslation::getTitle)
					.findFirst().get()
			);
			groupMap.put("id", group.getId());
		}
		
		coursesMap = new TreeMap<>(coursesMap);
		model.addAttribute("coursesMap", coursesMap);
		return "major-groups";
	}
}
