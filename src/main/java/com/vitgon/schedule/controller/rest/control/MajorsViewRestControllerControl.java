package com.vitgon.schedule.controller.rest.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.service.MajorConverterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/majors/view")
public class MajorsViewRestControllerControl {
	
	private MajorConverterService majorConverterService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getSchoolsView(@RequestParam("localeId") int localeId) {
		if (localeId < 0) {
			throw new IllegalArgumentException("Locale id must be equal or greater than 0!");
		}
		
		ModelAndView model = new ModelAndView("control/majors-list :: majors-list");
		List<MajorDto> majorDtoList = null;
		
		if (localeId == 0) {
			majorDtoList = majorConverterService.convertAllToDtoList();
		} else {
			majorDtoList = majorConverterService.convertAllToDtoList(localeId);
		}
		
		model.addObject("majorDtoList", majorDtoList);
		return model;
	}
}
