package com.vitgon.schedule.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseNum implements Comparable<CourseNum> {
	private int number;

	@Override
	public int compareTo(CourseNum o) {
		if (this.number > o.number) return 1;
		if (this.number < o.number) return -1;
		return 0;
	}
}
