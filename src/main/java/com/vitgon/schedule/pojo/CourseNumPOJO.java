package com.vitgon.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseNumPOJO implements Comparable<CourseNumPOJO> {
	private int number;

	@Override
	public int compareTo(CourseNumPOJO o) {
		if (this.number > o.number) return 1;
		if (this.number < o.number) return -1;
		return 0;
	}
}
