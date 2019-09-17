package com.vitgon.schedule.model.database.translation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;

import javax.persistence.*;
import java.util.Objects;


@Entity(name = "major_translations")
@Table(name = "major_translations")
public class MajorTranslation {
	
	@EmbeddedId
	private MajorTranslationId majorTranslationId;
	
	@Column(name = "translation")
	private String translation;

	public MajorTranslation() {
		super();
	}

	public MajorTranslation(Major major, Locale locale, String translation) {
		super();
		this.majorTranslationId = new MajorTranslationId(major, locale);
		this.translation = translation;
	}

	public MajorTranslationId getMajorTranslationId() {
		return majorTranslationId;
	}

	public void setMajorTranslationId(MajorTranslationId majorTranslationId) {
		this.majorTranslationId = majorTranslationId;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MajorTranslation that = (MajorTranslation) o;
		return Objects.equals(majorTranslationId, that.majorTranslationId) &&
				Objects.equals(translation, that.translation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(majorTranslationId, translation);
	}
}
