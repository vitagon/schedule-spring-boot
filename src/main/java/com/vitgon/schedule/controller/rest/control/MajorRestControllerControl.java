package com.vitgon.schedule.controller.rest.control;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.MajorMapperService;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control")
public class MajorRestControllerControl {
	
	private MajorService majorService;
	private LocaleService localeService;
	private MessageService messageService;
	
	private MajorMapperService majorMapperService;
	private SchoolService schoolService;
	
	@PostMapping("/major")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiSuccess addSchool(@RequestBody @Valid AddMajorDto addMajorDto) {
		School school = schoolService.findById(addMajorDto.getSchoolId());
		
		Major major = new Major();
		major.setSchool(school);
		major.setName(addMajorDto.getName().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(addMajorDto.getName()));
		major.setDuration(addMajorDto.getDuration());
		major.setDegree(DegreeEnum.valueOf(addMajorDto.getDegree()));
		majorService.save(major);
		return new ApiSuccess(new Date(), "You successfully added major!");
	}
	
	@PutMapping("/major")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateSchool(@RequestBody @Valid EditMajorDto editMajorDto) {
		Major major = majorService.findById(editMajorDto.getId());
		if (major == null) {
			throw new IllegalArgumentException(String.format("Major with id=%d name doesn't exist!", editMajorDto.getId()));
		}
		major.setName(editMajorDto.getNewMajorName().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(editMajorDto.getNewMajorName()));
		major.setDuration(editMajorDto.getDuration());
		major.setDegree(DegreeEnum.valueOf(editMajorDto.getDegree()));
		majorService.update(major);
		return new ApiSuccess(new Date(), "You successfully updated major!");
	}
	
	@DeleteMapping("/major")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteSchool(@RequestParam("id") Major major, HttpServletRequest request) {
		if (major == null) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("majorId", Arrays.asList(messageService.getMessage("chooseValue", request)));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(new Date(), "Major Not Found", errors));
		}
		majorService.delete(major);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed major!"));
	}

	@GetMapping("/majors/view")
	public ModelAndView getSchoolsView(@RequestParam("localeId") int localeId) {
		if (localeId < 0) {
			throw new IllegalArgumentException("Locale id must be equal OR greater than 0!");
		}
		
		ModelAndView model = new ModelAndView("control/schools-list :: schools-list");
		List<MajorDto> majorDtoList = null;
		
		if (localeId == 0) {
			majorDtoList = majorMapperService.mapAllMajorsToMajorDtoList();
		} else {
			Locale locale = localeService.findById(localeId);
			if (locale == null) {
				throw new IllegalArgumentException(String.format("Locale with id=%d was not found!", localeId));
			}
			majorDtoList = majorMapperService.mapAllMajorsToMajorDtoList(locale);
		}
		
		model.addObject("majorDtoList", majorDtoList);
		return model;
	}
}
