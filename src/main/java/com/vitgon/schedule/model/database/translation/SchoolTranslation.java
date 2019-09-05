package com.vitgon.schedule.model.database.translation;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"school"})
@Entity(name = "school_translations")
@Table(name = "school_translations")
public class SchoolTranslation {
	
	@EmbeddedId
	private SchoolTranslationId schoolTranslationId;
	
	@Column(name = "translation")
	private String translation;
	
	public SchoolTranslation(School school, Locale locale, String translation) {
		this.schoolTranslationId = new SchoolTranslationId(school, locale);
		this.translation = translation;
	}
}
