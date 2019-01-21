package com.vitgon.schedule.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.Latin;
import com.vitgon.schedule.annotation.UniqueSubject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddSubjectDTO {
	
	@UniqueSubject(message = "{Unique.subject.subjectName}")
	@NotEmpty(message = "{NotEmpty.subject.subjectName}")
	@Size(min = 5, max = 40, message = "{Size.subject.subjectName}")
	@Latin(message = "{Latin.subject.subjectName}")
	private String subjectName;
}
