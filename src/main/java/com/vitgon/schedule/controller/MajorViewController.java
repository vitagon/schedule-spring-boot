package com.vitgon.schedule.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.pojo.CourseNumPOJO;
import com.vitgon.schedule.pojo.GroupPOJO;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.util.GroupUtil;

@Controller
public class MajorViewController {
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private LocaleService localeService;
	
	@RequestMapping("/school/{school}/major/{major}")
	public String showMajorGroups(@PathVariable("major") String majorUrl, HttpServletRequest request, Model model) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		
		Major major = majorService.findByUrl(majorUrl);
		Map<CourseNumPOJO, List<GroupPOJO>> groupsMap = GroupUtil.prepareGroupPojos(major.getGroups(), locale);
		
		model.addAttribute("groupsMap", groupsMap);
		return "major-groups";
	}
}
