package com.vitgon.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.translation.GroupTranslation;
import com.vitgon.schedule.model.translation.MajorTranslation;
import com.vitgon.schedule.model.translation.SchoolTranslation;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.model.translation.TeacherTranslation;

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
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@JsonBackReference
	@OneToMany(mappedBy = "locale")
	private List<MajorTranslation> majorTranslations = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "locale")
	private List<SchoolTranslation> schoolTranslations = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "locale")
	private List<SubjectTranslation> subjectTranslations = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "locale")
	private List<GroupTranslation> groupTranslations = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "locale")
	private List<TeacherTranslation> teacherTranslations = new ArrayList<>();

	public Locale(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
}
