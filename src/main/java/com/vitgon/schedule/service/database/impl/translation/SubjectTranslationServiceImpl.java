package com.vitgon.schedule.service.database.impl.translation;

import com.vitgon.schedule.dao.translation.SubjectTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectTranslationServiceImpl implements SubjectTranslationService {

	private final SubjectTranslationDao subjectTranslDao;

	public SubjectTranslationServiceImpl(SubjectTranslationDao subjectTranslDao) {
		this.subjectTranslDao = subjectTranslDao;
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
	public Optional<SubjectTranslation> findById(SubjectTranslationId id) {
		return subjectTranslDao.findById(id);
	}

	@Override
	public List<SubjectTranslation> findAll() {
		return subjectTranslDao.findAll();
	}
	
	@Override
	public Optional<SubjectTranslation> findByLocaleCodeAndSubjectId(String localeCode, Integer subjectId) {
		return subjectTranslDao.findByLocaleCodeAndSubjectId(localeCode, subjectId);
	}
	
	@Override
	public Optional<SubjectTranslation> findByLocaleIdAndSubjectId(Integer localeId, Integer subjectId) {
		return subjectTranslDao.findByLocaleIdAndSubjectId(localeId, subjectId);
	}
	
	@Override
	public Optional<SubjectTranslation> findByLocaleAndSubject(Locale locale, Subject subject) {
		return subjectTranslDao.findByLocaleIdAndSubjectId(locale.getId(), subject.getId());
	}
	
	/**
	 * Get subject title translation
	 * If translation doesn't exist then take default English title
	 * 
	 * @param subject
	 * @param locale
	 * @param substituteNull
	 * @return
	 */
	@Override
	public String getSubjectTitle(Subject subject, Locale locale, boolean substituteNull) {
		String subjectTitle = null;
		
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			subjectTitle = subject.getName();
		} else {
			Optional<SubjectTranslation> translation = findByLocaleAndSubject(locale, subject);
			if (!translation.isPresent()) {
				if (substituteNull)
					subjectTitle = subject.getName();
				else
					return null;
			} else {
				subjectTitle = translation.get().getTranslation();
			}
		}
		
		return subjectTitle.substring(0, 1).toUpperCase() + subjectTitle.substring(1);
	}

	@Override
	public SubjectTranslation findByTitle(String translation) {
		return subjectTranslDao.findByTranslation(translation);
	}
	
	@Override
	public void delete(SubjectTranslation obj) {
		subjectTranslDao.delete(obj);
	}

	@Override
	public void deleteById(SubjectTranslationId id) {
		subjectTranslDao.deleteById(id);
	}

	@Override
	public void save(Integer subjectId, Integer localeId, String title) {
		subjectTranslDao.save(subjectId, localeId, title);
	}

	@Override
	public void deleteBySubjectIdAndLocaleId(Integer subjectId, Integer localeId) {
		subjectTranslDao.deleteBySubjectIdAndLocaleId(subjectId, localeId);
	}
}
