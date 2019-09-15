package com.vitgon.schedule.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.projection.SchoolProjection;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.SchoolDtoService;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@Controller
public class SchoolsViewController {
	
	private SchoolDtoService schoolDtoService;
	private SchoolService schoolService;

	public SchoolsViewController(SchoolDtoService schoolDtoService, SchoolService schoolService) {
		this.schoolDtoService = schoolDtoService;
		this.schoolService = schoolService;
	}

	@RequestMapping("/schools")
	public String showSchoolsPage(Model model) {
		List<SchoolProjection> schools = schoolService.getAllJoiningWithMajors();
		List<SchoolDto> schoolDtoList = schoolDtoService.getSchoolDtoListForPublic(schools);
		model.addAttribute("schools", schoolDtoList);
		return "schools";
	}
}
