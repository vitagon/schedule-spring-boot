package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.UserTranslation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"name", "code"})
@Entity
@Table(name = "locales")
public class Locale extends BaseModel<Integer> {
	
	@Column(name = "code")
	private String code;
	
	@JsonIgnore
	@OneToMany(mappedBy = "locale")
	private List<MajorTranslation> majorTranslations = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "schoolTranslationId.locale")
	private List<SchoolTranslation> schoolTranslations = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "subjectTranslationId.locale")
	private List<SubjectTranslation> subjectTranslations = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "userTranslationId.locale")
	private List<UserTranslation> teacherTranslations = new ArrayList<>();

	public Locale(String code) {
		this.code = code;
	}
}
