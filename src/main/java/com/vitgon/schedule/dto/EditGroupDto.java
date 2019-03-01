package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditGroupDto {
	
	@Min(value = 1, message = "{NotEmpty.default}")
	private int id;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 7, message = "{NotEmpty.default}")
	private int courseNum;
}
