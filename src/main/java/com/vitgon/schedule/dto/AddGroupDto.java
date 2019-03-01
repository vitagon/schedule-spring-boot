package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGroupDto {
	
	@Min(value = 1, message = "{NotEmpty.default}")
	private int majorId;
	
	@Min(value = 1000, message = "{NotEmpty.default}")
	@Max(value = 9999, message = "{NotEmpty.default}")
	private int number;
	
	@NotEmpty(message = "{NotEmpty.default}")
	private String suffix;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 7, message = "{NotEmpty.default}")
	private int courseNum;
}
