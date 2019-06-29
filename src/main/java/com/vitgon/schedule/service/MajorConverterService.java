package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MajorConverterService {
	
	private MajorService majorService;
	private LocaleService localeService;

//	public List<MajorDtoControl> convertToMajorDtoControlList() {
//		List<Major> majors = majorService.findAll();
//		List<MajorDtoControl> majorDtoControlList = new ArrayList<>();
//		for (Major major : majors) {
//			MajorDtoControl majorDto = new MajorDtoControl(major.getId(), major.getName(), null);
//			majorDtoControlList.add(majorDto);
//		}
//		return majorDtoControlList;
//	}
	
	
//	
//	public List<MajorDtoControl> convertToMajorDtoControlList(int localeId) {
//		Optional<Locale> locale = localeService.findById(localeId);
//		if (!locale.isPresent()) {
//			throw new IllegalArgumentException(String.format("Locale with id=%d was not found!", localeId));
//		}
//		return convertToMajorDtoControlList(locale.get());
//	}
}