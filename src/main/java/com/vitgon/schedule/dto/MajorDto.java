package com.vitgon.schedule.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.view.Views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
	private Integer id;
	private String name;
	private String url;
	private DegreeEnum degree;
	private Integer duration;
	private String translation;
	private Integer schoolId;
	private String schoolName;
}
