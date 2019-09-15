package com.vitgon.schedule.model.database.translation.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
public class SubjectTranslationId implements Serializable {
	
	private static final long serialVersionUID = -7802327655330293030L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;

	public SubjectTranslationId() {
		super();
	}

	public SubjectTranslationId(Subject subject, Locale locale) {
		super();
		this.subject = subject;
		this.locale = locale;
	}

	public Subject getSubject() {
		return subject;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		SubjectTranslationId other = (SubjectTranslationId) obj;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
}
