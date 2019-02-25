package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.EditMajorDto;
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
		School school = schoolService.findById(addMajorDto.getSchoolId());
		
		Major major = new Major();
		major.setSchool(school);
		major.setName(addMajorDto.getName().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(addMajorDto.getName()));
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
}
