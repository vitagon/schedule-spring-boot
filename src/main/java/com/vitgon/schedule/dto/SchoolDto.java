package com.vitgon.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.view.Views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchoolDto {
	
	@JsonView(Views.Public.class)
	private int id;
	
	@JsonView(Views.Public.class)
	private String name;
	
	@JsonView(Views.Public.class)
	private String url;
	
	@JsonView(Views.AdminPanel.class)
	private String translation;
	
	@JsonView(Views.Public.class)
	private List<MajorDto> majors = new ArrayList<>();
}
