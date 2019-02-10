package com.vitgon.schedule.converter.util;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateScheduleDTOAuxiliary implements Serializable {
	private static final long serialVersionUID = 7274534115141402913L;
	
	private int groupId;
	private String week;
	private int dayNum;
	private int lessonNum;
	
	private int subjectId;
	private String lessonType;
	private int userId;
	private String classroom;
}
