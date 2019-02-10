package com.vitgon.schedule.converter.util;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EditScheduleDTOAuxiliary implements Serializable {
	private static final long serialVersionUID = 7274534115141402913L;
	
	private int scheduleId;
	
	private int subjectId;
	private String lessonType;
	private int userId;
	private String classroom;
}
