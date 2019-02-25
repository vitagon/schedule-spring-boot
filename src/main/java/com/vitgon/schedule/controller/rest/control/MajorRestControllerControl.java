package com.vitgon.schedule.controller.rest.control;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.MajorDtoConverterService;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/major")
public class MajorRestControllerControl {
	
	private MajorService majorService;
	private MessageService messageService;
	private MajorDtoConverterService majorDtoConverterService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiSuccess addSchool(@RequestBody @Valid AddMajorDto addMajorDto) {
		Major major = majorDtoConverterService.convertToEntity(addMajorDto);
		majorService.save(major);
		return new ApiSuccess(new Date(), "You successfully added major!");
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateSchool(@RequestBody @Valid EditMajorDto editMajorDto) {
		Major major = majorService.findById(editMajorDto.getId());
		if (major == null) {
			throw new IllegalArgumentException(String.format("Major with id=%d name doesn't exist!", editMajorDto.getId()));
		}
		major = majorDtoConverterService.convertToEntity(editMajorDto, major);
		majorService.update(major);
		return new ApiSuccess(new Date(), "You successfully updated major!");
	}
	
	@DeleteMapping
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
}
