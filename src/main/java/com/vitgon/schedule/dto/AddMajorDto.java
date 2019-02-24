package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.EnumMatch;
import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueMajor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMajorDto {
	
	@Min(value = 1, message = "{NotEmpty.default}")
	private int schoolId;
	
	@UniqueMajor(message = "{Duplicate.major}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 10, message = "{Max.default}")
	private int duration;
	
	@NotNull(message = "{NotNull.default}")
	@EnumMatch(enumClazz = DegreeEnum.class, message = "{Degree.noMatch}")
	private String degree;
}
