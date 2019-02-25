package com.vitgon.schedule.controller.rest.control;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.controller.rest.SubjectRestController;
import com.vitgon.schedule.dto.AddSubjectDto;
import com.vitgon.schedule.dto.EditSubjectDto;
import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control")
public class SubjectRestControllerControl {
	
	private SubjectRestController subjectRestController;
	private SubjectService subjectService;
	private MessageService messageService;

	@PostMapping("/subject")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiSuccess addSubject(@RequestBody @Valid AddSubjectDto addSubjectDTO) {
		subjectService.save(new Subject(addSubjectDTO.getSubjectName().toLowerCase()));
		return new ApiSuccess(new Date(), "You successfully added subject!");
	}
	
	@PutMapping("/subject")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess update(@RequestBody @Valid EditSubjectDto editSubjectDTO) {
		Subject subject = subjectService.findById(editSubjectDTO.getOldSubjectId());
		if (subject == null) {
			throw new IllegalArgumentException("Subject with such name doesn't exist!");
		}
		subject.setName(editSubjectDTO.getNewSubjectName());
		subjectService.update(subject);
		return new ApiSuccess(new Date(), "You successfully edited subject!");
	}
	
	@DeleteMapping(value = "/subject", params = {"id"})
	public ResponseEntity<?> delete(@RequestParam("id") Subject subject, HttpServletRequest request) {
		if (subject == null) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("subjectId", Arrays.asList(messageService.getMessage("chooseValue", request)));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(new Date(), "Subject Not Found", errors));
		}
		subjectService.delete(subject);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed subject!"));
	}
	
	@GetMapping("/subjects/view")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getSubjectsWithView(@RequestParam int localeId) {
		List<SubjectDto> subjects = subjectRestController.getSubjectDTOListByLocale(localeId);
		ModelAndView model = new ModelAndView("control/subjects-list :: subjects-list");
		model.addObject("subjects", subjects);
		return model;
	}
}
