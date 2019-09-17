package com.vitgon.schedule.model.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name = "subject_translations")
@Table(name = "subject_translations")
public class SubjectTranslation {
	
	@EmbeddedId
	private SubjectTranslationId subjectTranslationId;
	
	@Column(name = "translation")
	private String translation;
	
	public SubjectTranslation() {
		super();
	}

	public SubjectTranslation(Subject subject, Locale locale, String translation) {
		this.subjectTranslationId = new SubjectTranslationId(subject, locale);
		this.translation = translation;
	}

	public SubjectTranslationId getSubjectTranslationId() {
		return subjectTranslationId;
	}

	public void setSubjectTranslationId(SubjectTranslationId subjectTranslationId) {
		this.subjectTranslationId = subjectTranslationId;
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
		result = prime * result + ((subjectTranslationId == null) ? 0 : subjectTranslationId.hashCode());
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
		SubjectTranslation other = (SubjectTranslation) obj;
		if (subjectTranslationId == null) {
			if (other.subjectTranslationId != null)
				return false;
		} else if (!subjectTranslationId.equals(other.subjectTranslationId))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}
}
