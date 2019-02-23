package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueMajor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddMajorDto {
	
	@Min(value = 1, message = "{NotEmpty.default}")
	private int schoolId;

	@Min(value = 1, message = "{NotEmpty.default}")
	private int id;
	
	@UniqueMajor(message = "{Duplicate.major}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;
	
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String url;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 10, message = "{Max.default}")
	private int duration;
	
	@NotEmpty(message = "{NotEmpty.default}")
	private DegreeEnum degree;
}
