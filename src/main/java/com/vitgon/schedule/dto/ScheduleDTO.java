package com.vitgon.schedule.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ScheduleDTO implements Serializable {
	private static final long serialVersionUID = 892216499352228418L;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int groupId;
	
	@NotEmpty(message = "{NotEmpty.default}")
	@Pattern(regexp = "^up|down$")
	private String week;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int dayNum;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int lessonNum;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int subjectId;
	
	@Pattern(regexp = "^[lecture|practice|0]?$")
	private String lessonType;
	
	@PositiveOrZero
	private int userId;
	
	@Pattern(regexp = "^([A-Z]{1}[0-9]{3,4})?$")
	private String classroom;
}
