package com.vitgon.schedule.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.UserTranslation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "locales")
public class Locale extends BaseModel<Integer> {
	
	@Column(name = "code")
	private String code;
	
	@JsonIgnore
	@OneToMany(mappedBy = "majorTranslationId.locale")
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

	public Locale() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<MajorTranslation> getMajorTranslations() {
		return majorTranslations;
	}

	public void setMajorTranslations(List<MajorTranslation> majorTranslations) {
		this.majorTranslations = majorTranslations;
	}

	public List<SchoolTranslation> getSchoolTranslations() {
		return schoolTranslations;
	}

	public void setSchoolTranslations(List<SchoolTranslation> schoolTranslations) {
		this.schoolTranslations = schoolTranslations;
	}

	public List<SubjectTranslation> getSubjectTranslations() {
		return subjectTranslations;
	}

	public void setSubjectTranslations(List<SubjectTranslation> subjectTranslations) {
		this.subjectTranslations = subjectTranslations;
	}

	public List<UserTranslation> getTeacherTranslations() {
		return teacherTranslations;
	}

	public void setTeacherTranslations(List<UserTranslation> teacherTranslations) {
		this.teacherTranslations = teacherTranslations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((majorTranslations == null) ? 0 : majorTranslations.hashCode());
		result = prime * result + ((schoolTranslations == null) ? 0 : schoolTranslations.hashCode());
		result = prime * result + ((subjectTranslations == null) ? 0 : subjectTranslations.hashCode());
		result = prime * result + ((teacherTranslations == null) ? 0 : teacherTranslations.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locale other = (Locale) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (majorTranslations == null) {
			if (other.majorTranslations != null)
				return false;
		} else if (!majorTranslations.equals(other.majorTranslations))
			return false;
		if (schoolTranslations == null) {
			if (other.schoolTranslations != null)
				return false;
		} else if (!schoolTranslations.equals(other.schoolTranslations))
			return false;
		if (subjectTranslations == null) {
			if (other.subjectTranslations != null)
				return false;
		} else if (!subjectTranslations.equals(other.subjectTranslations))
			return false;
		if (teacherTranslations == null) {
			if (other.teacherTranslations != null)
				return false;
		} else if (!teacherTranslations.equals(other.teacherTranslations))
			return false;
		return true;
	}
}
