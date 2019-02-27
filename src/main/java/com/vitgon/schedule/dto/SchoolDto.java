package com.vitgon.schedule.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchoolDto {
	
	private int id;
	private String name;
	private String translation;
	private String url;
	private List<MajorDto> majors;
	
	public SchoolDto(Integer id, String name, String translation) {
		this.id = id;
		this.name = name;
		this.translation = translation;
	}
}
