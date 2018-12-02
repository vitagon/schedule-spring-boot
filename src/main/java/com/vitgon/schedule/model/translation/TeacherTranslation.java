package com.vitgon.schedule.model.translation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Teacher;
import com.vitgon.schedule.model.translation.pk.TeacherTranslationId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"teacher"})
@Entity
@Table(name = "teacher_translations")
@IdClass(TeacherTranslationId.class)
public class TeacherTranslation {
	
	@Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "middlename")
	private String middlename;
}
