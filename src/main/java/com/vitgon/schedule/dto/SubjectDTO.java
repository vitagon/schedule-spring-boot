package com.vitgon.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
	private int id;
	private String name;
	private String translation;
	
	public SubjectDTO(int id, String name) {
		this.id = id;
		this.name = name;
		this.translation = null;
	}
}
