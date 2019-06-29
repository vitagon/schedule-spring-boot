package com.vitgon.schedule.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EditScheduleDto implements Serializable {
	private static final long serialVersionUID = 892216499352228418L;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int groupId;
	
	@NotEmpty(message = "{NotEmpty.default}")
	@Pattern(regexp = "^up|down$")
	private String week;
	
	@NotNull(message = "{NotNull.default}")
	private Days day;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int lessonNum;
	
	
	
	@Min(value = 1, message = "{NotNull.default}")
	private Integer subjectId;
	
	@PositiveOrZero
	private Integer lessonTypeId;
	
	@PositiveOrZero
	private int userId;
	
	@Pattern(regexp = "^([A-Z]{1}[0-9]{3,4})?$")
	private String classroom;
}
