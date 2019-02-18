package com.vitgon.schedule.controller.rest.control;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.AddSubjectDTO;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/subject")
public class SubjectRestControllerControl {
	
	private SubjectService subjectService;

	@PostMapping
	public ApiSuccess addSubject(@RequestBody @Valid AddSubjectDTO addSubjectDTO) {
		subjectService.save(new Subject(addSubjectDTO.getSubjectName().toLowerCase()));
		return new ApiSuccess(new Date(), "You successfully added subject!");
	}
}
