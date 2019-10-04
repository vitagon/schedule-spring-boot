package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.projection.MajorProjection;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;

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

	public Major convertToEntity(MajorDto majorDto) {
		Optional<School> school = schoolService.findById(majorDto.getSchoolId());
		
		Major major = new Major();
		major.setSchool(school.get());
		major.setName(majorDto.getName().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(majorDto.getName()));
		major.setDuration(majorDto.getDuration());
		major.setDegree(DegreeEnum.valueOf(majorDto.getDegree()));
		return major;
	}
	
	public Major convertToEntity(MajorDto majorDto, Major major) {
		major.setName(majorDto.getName().toLowerCase());
		major.setUrl(StringUtil.applyUnderlyingStyle(majorDto.getName()));
		major.setDuration(majorDto.getDuration());
		major.setDegree(DegreeEnum.valueOf(majorDto.getDegree()));

		School school = schoolService.findById(majorDto.getSchoolId()).get();
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
	
	
	public List<MajorDto> getAllByLocaleId(Integer localeId) {
		List<MajorProjection> majors = majorService.getAllLeftJoiningOnLocaleId(localeId);
		List<MajorDto> majorDtoListAdminPanel = new ArrayList<>();
		for (MajorProjection major : majors) {
			MajorDto majorDto = new MajorDto();
			majorDto.setId(major.getId());
			majorDto.setName(major.getName());
			majorDto.setTranslation(major.getTranslation());
			majorDto.setDuration(major.getDuration());
			majorDto.setDegree(major.getDegree().name());
			majorDto.setSchoolId(major.getSchoolId());
			majorDto.setSchoolName(major.getSchoolName());
			majorDtoListAdminPanel.add(majorDto);
		}
		return majorDtoListAdminPanel;
	}
	
	public List<MajorDto> getAllByDefaultLocale() {
		Locale locale = localeConverterService.getClientLocale();
		List<MajorProjection> majors = majorService.getAllLeftJoiningOnLocaleId(locale.getId());
		List<MajorDto> majorDtoListAdminPanel = new ArrayList<>();
		for (MajorProjection major : majors) {
			MajorDto majorDto = new MajorDto();
			majorDto.setId(major.getId());
			majorDto.setName(major.getName());
			majorDto.setTranslation(major.getTranslation());
			majorDto.setDuration(major.getDuration());
			majorDto.setDegree(major.getDegree().name());
			majorDto.setSchoolId(major.getSchoolId());
			majorDto.setSchoolName(major.getSchoolName());
			majorDtoListAdminPanel.add(majorDto);
		}
		return majorDtoListAdminPanel;
	}

	public MajorDto getById(Integer majorId) {
		Locale locale = localeConverterService.getClientLocale();
		Optional<MajorProjection> majorProjectionOpt = majorService.getByLocaleIdAndMajorId(locale.getId(), majorId);
		if (majorProjectionOpt.isPresent()) {
			MajorProjection majorProj = majorProjectionOpt.get();
			MajorDto majorDto = new MajorDto();
			majorDto.setId(majorProj.getId());
			majorDto.setName(majorProj.getName());
			majorDto.setTranslation(majorProj.getTranslation());
			majorDto.setDuration(majorProj.getDuration());
			majorDto.setDegree(majorProj.getDegree().name());
			majorDto.setSchoolId(majorProj.getSchoolId());
			majorDto.setSchoolName(majorProj.getSchoolName());
			return majorDto;
		}
		return null;
	}
}
