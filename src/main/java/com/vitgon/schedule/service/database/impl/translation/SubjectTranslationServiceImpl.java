package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.SubjectTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@Service
@Transactional
public class SubjectTranslationServiceImpl implements SubjectTranslationService {

	private final SubjectTranslationDao subjectTranslDao;
	private final SubjectService subjectService;
	private final LocaleService localeService;
	
	@Autowired
	public SubjectTranslationServiceImpl(SubjectTranslationDao subjectTranslDao,
										 SubjectService subjectService,
										 LocaleService localeService) {
		this.subjectTranslDao = subjectTranslDao;
		this.subjectService = subjectService;
		this.localeService = localeService;
	}
	
	@Override
	public SubjectTranslation save(SubjectTranslation obj) {
		return subjectTranslDao.save(obj);
	}

	@Override
	public SubjectTranslation update(SubjectTranslation obj) {
		return subjectTranslDao.save(obj);
	}

	@Override
	public SubjectTranslation findById(SubjectTranslationId id) {
		return subjectTranslDao.findById(id).get();
	}

	@Override
	public List<SubjectTranslation> findAll() {
		return subjectTranslDao.findAll();
	}
	
	@Override
	public SubjectTranslation findByLangCodeAndSubjectId(String langCode, int subjectId) {
		Subject subject = subjectService.findById(subjectId);
		Locale locale = localeService.findByCode(langCode);
		if (subject == null || locale == null) {
			throw new IllegalArgumentException("Locale or subject were not found!");
		}
		return subjectTranslDao.findBySubjectTranslationIdLocaleAndSubjectTranslationIdSubject(locale, subject);
	}
	
	@Override
	public SubjectTranslation findByLocaleAndSubject(Locale locale, Subject subject) {
		if (subject == null || locale == null) {
			throw new IllegalArgumentException("Locale or subject were not found!");
		}
		return subjectTranslDao.findBySubjectTranslationIdLocaleAndSubjectTranslationIdSubject(locale, subject);
	}
	
	/**
	 * Get subject title translation
	 * If translation doesn't exist then take default English title
	 * 
	 * @param subject
	 * @param locale
	 * @return
	 */
	@Override
	public String getSubjectTitle(Subject subject, Locale locale) {
		String subjectTitle = null;
		
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			subjectTitle = subject.getName();
		} else {
			SubjectTranslation translation = findByLocaleAndSubject(locale, subject);
			subjectTitle = translation.getTitle();
		}
		
		return subjectTitle.substring(0, 1).toUpperCase() + subjectTitle.substring(1);
	}

	@Override
	public SubjectTranslation findByTitle(String title) {
		return subjectTranslDao.findByTitle(title);
	}
}
