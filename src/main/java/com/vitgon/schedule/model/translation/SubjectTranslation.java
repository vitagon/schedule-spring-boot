package com.vitgon.schedule.model.translation;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.pk.SubjectTranslationId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject_translations")
public class SubjectTranslation {
	
	@EmbeddedId
	private SubjectTranslationId subjectTranslationId;
	
	@Column(name = "title")
	private String title;

	public SubjectTranslation(Subject subject, Locale locale, String title) {
		this.subjectTranslationId = new SubjectTranslationId(subject, locale);
		this.title = title;
	}
}
