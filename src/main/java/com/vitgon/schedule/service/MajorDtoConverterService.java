package com.vitgon.schedule.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MajorDtoConverterService {
	
	private SchoolService schoolService;

	public Major convertToEntity(AddMajorDto addMajorDto) {
		Optional<School> school = schoolService.findById(addMajorDto.getSchoolId());
		
		Major major = new Major();
		major.setSchool(school.get());
		major.setName(addMajorDto.getTitle().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(addMajorDto.getTitle()));
		major.setDuration(addMajorDto.getDuration());
		major.setDegree(DegreeEnum.valueOf(addMajorDto.getDegree()));
		return major;
	}
	
	public Major convertToEntity(EditMajorDto editMajorDto, Major major) {
		major.setName(editMajorDto.getName().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(editMajorDto.getName()));
		major.setDuration(editMajorDto.getDuration());
		major.setDegree(DegreeEnum.valueOf(editMajorDto.getDegree()));
		return major;
	}
	
	public MajorDto convertToDto(Major major) {
		MajorDto majorDto = new MajorDto();
		majorDto.setId(major.getId());
		majorDto.setName(major.getName());
		majorDto.setTranslation(null);
		majorDto.setUrl(major.getUrl());
		return majorDto;
	}
}
