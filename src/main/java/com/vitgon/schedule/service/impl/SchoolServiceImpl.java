package com.vitgon.schedule.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.dao.translation.SchoolTranslationDao;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.model.translation.MajorTranslation;
import com.vitgon.schedule.model.translation.SchoolTranslation;
import com.vitgon.schedule.service.SchoolService;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private final SchoolDao schoolDao;
	
	@Autowired
	private final SchoolTranslationDao schoolTranslDao;
	
	public SchoolServiceImpl(SchoolDao schoolDao, SchoolTranslationDao schoolTranslDao) {
		this.schoolDao = schoolDao;
		this.schoolTranslDao = schoolTranslDao;
	}
	
	@Override
	public School save(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public School update(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public School findById(Integer id) {
		return schoolDao.findById(id).get();
	}

	@Override
	public List<School> findAll() {
		return schoolDao.findAll();
	}

	@Override
	public School findByTitle(String title) {
		SchoolTranslation schoolTransl = schoolTranslDao.findByTitle(title);
		return schoolTransl.getSchool();
	}

	@Override
	public Map<Integer, Map<String, Object>> findAllByLocale(Locale locale) {
		List<School> schools = schoolDao.findAllByLocale(locale);
		Map<Integer, Map<String, Object>> schoolsMap = new HashMap<>();
		
		for (School school : schools) {
			Map<String, Object> schoolMap = new HashMap<>();
			schoolMap.put("id", school.getId().toString());
			schoolMap.put("url", school.getUrl()); 
			schoolMap.put("title", school.getSchoolTranslations().stream()
					.filter(x -> locale == x.getLocale())
					.map(SchoolTranslation::getTitle)
					.findFirst().get()
			);
			
			List<Major> majors = school.getMajors();
			if (majors != null) {
				List<String> majorsList = new ArrayList<>();
				for (Major major : majors) {
					majorsList.add(major.getMajorTranslations().stream()
							.filter(x -> locale == x.getLocale())
							.map(MajorTranslation::getTitle)
							.findFirst().get()
					);
				}
				schoolMap.put("majors", majorsList);
			}
			
			
			schoolsMap.put(school.getId(), schoolMap);
		}
		return schoolsMap;
	}
}
