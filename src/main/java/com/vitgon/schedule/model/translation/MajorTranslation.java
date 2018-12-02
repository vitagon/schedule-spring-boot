package com.vitgon.schedule.model.translation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.model.translation.pk.MajorTranslationId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"major"})
@Entity
@Table(name = "major_translations")
@IdClass(MajorTranslationId.class)
public class MajorTranslation {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "major_id")
	@JsonBackReference
	private Major major;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;
	
	@Column(name = "title")
	private String title;

	public MajorTranslation(Major major, Locale locale, String title) {
		super();
		this.major = major;
		this.locale = locale;
		this.title = title;
	}
}
