package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.dto.DegreeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DegreeRestController {

	@GetMapping("/api/degrees")
	public List<String> getDegrees() {
		return createDegreeList();
	}
	
	private List<String> createDegreeList() {
		DegreeEnum[] degreeEnumArr = DegreeEnum.values();
		List<String> degreeList = new ArrayList<>();
		for (DegreeEnum degreeEnum : degreeEnumArr) {
			degreeList.add(degreeEnum.toString());
		}
		return degreeList;
	}
}
