package com.vitgon.schedule.model.database.translation;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subject_translations")
@Table(name = "subject_translations")
public class SubjectTranslation {
	
	@EmbeddedId
	private SubjectTranslationId subjectTranslationId;
	
	@Column(name = "translation")
	private String translation;

	public SubjectTranslation(Subject subject, Locale locale, String translation) {
		this.subjectTranslationId = new SubjectTranslationId(subject, locale);
		this.translation = translation;
	}
}
