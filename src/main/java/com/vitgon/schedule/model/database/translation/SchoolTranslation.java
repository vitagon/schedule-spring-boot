package com.vitgon.schedule.model.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name = "school_translations")
@Table(name = "school_translations")
public class SchoolTranslation {
	
	@EmbeddedId
	private SchoolTranslationId schoolTranslationId;
	
	@Column(name = "translation")
	private String translation;
	
	public SchoolTranslation() {
		super();
	}

	public SchoolTranslation(School school, Locale locale, String translation) {
		this.schoolTranslationId = new SchoolTranslationId(school, locale);
		this.translation = translation;
	}

	public SchoolTranslation(SchoolTranslationId schoolTranslationId, String translation) {
		super();
		this.schoolTranslationId = schoolTranslationId;
		this.translation = translation;
	}

	public SchoolTranslationId getSchoolTranslationId() {
		return schoolTranslationId;
	}

	public void setSchoolTranslationId(SchoolTranslationId schoolTranslationId) {
		this.schoolTranslationId = schoolTranslationId;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schoolTranslationId == null) ? 0 : schoolTranslationId.hashCode());
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolTranslation other = (SchoolTranslation) obj;
		if (schoolTranslationId == null) {
			if (other.schoolTranslationId != null)
				return false;
		} else if (!schoolTranslationId.equals(other.schoolTranslationId))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}
}
