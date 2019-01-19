package com.vitgon.schedule.dto.form;

import com.vitgon.schedule.annotation.UniqueSubject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddSubjectDTO {
	
	@UniqueSubject(message = "{Unique.subject.subjectName}")
	private String subjectName;
}
