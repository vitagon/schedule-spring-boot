package com.vitgon.schedule.model.database.translation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"school"})
@Entity
@Table(name = "school_translations")
@IdClass(SchoolTranslationId.class)
public class SchoolTranslation {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "school_id")
	@JsonBackReference
	private School school;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;
	
	@Column(name = "title")
	private String title;
	
	public SchoolTranslation(School school, Locale locale, String title) {
		super();
		this.school = school;
		this.locale = locale;
		this.title = title;
	}
}
