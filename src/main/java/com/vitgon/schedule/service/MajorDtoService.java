package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.projection.MajorProjection;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@Service
public class MajorDtoService {
	
	private SchoolService schoolService;
	private MajorService majorService;
	private LocaleConverterService localeConverterService;

	public MajorDtoService(SchoolService schoolService, MajorService majorService, LocaleConverterService localeConverterService) {
		this.schoolService = schoolService;
		this.majorService = majorService;
		this.localeConverterService = localeConverterService;
	}

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

		School school = schoolService.findById(editMajorDto.getSchoolId()).get();
		major.setSchool(school);
		return major;
	}
	
	public MajorDto convertToDto(Major major) {
		MajorDto majorDto = new MajorDto();
		majorDto.setId(major.getId());
		majorDto.setName(major.getName());
		majorDto.setUrl(major.getUrl());
		return majorDto;
	}
	
	
	public List<MajorDto> getAllByLocaleIdForAdminPanel(Integer localeId) {
		List<MajorProjection> majors = majorService.getAllLeftJoiningOnLocaleId(localeId);
		List<MajorDto> majorDtoListAdminPanel = new ArrayList<>();
		for (MajorProjection major : majors) {
			MajorDto majorDto = new MajorDto();
			majorDto.setId(major.getId());
			majorDto.setName(major.getName());
			majorDto.setTranslation(major.getTranslation());
			majorDto.setDuration(major.getDuration());
			majorDto.setDegree(major.getDegree());
			majorDto.setSchoolId(major.getSchoolId());
			majorDto.setSchoolName(major.getSchoolName());
			majorDtoListAdminPanel.add(majorDto);
		}
		return majorDtoListAdminPanel;
	}
	
	public List<MajorDto> getAllByLocaleIdForAdminPanel() {
		Locale locale = localeConverterService.getClientLocale();
		List<MajorProjection> majors = majorService.getAllLeftJoiningOnLocaleId(locale.getId());
		List<MajorDto> majorDtoListAdminPanel = new ArrayList<>();
		for (MajorProjection major : majors) {
			MajorDto majorDto = new MajorDto();
			majorDto.setId(major.getId());
			majorDto.setName(major.getName());
			majorDto.setTranslation(major.getTranslation());
			majorDto.setDuration(major.getDuration());
			majorDto.setDegree(major.getDegree());
			majorDto.setSchoolId(major.getSchoolId());
			majorDto.setSchoolName(major.getSchoolName());
			majorDtoListAdminPanel.add(majorDto);
		}
		return majorDtoListAdminPanel;
	}
}
