package com.vitgon.schedule.collection;

import com.vitgon.schedule.dto.ScheduleDto;
import com.vitgon.schedule.dto.TeacherDto;
import com.vitgon.schedule.util.ScheduleUtil;

public class ScheduleTreeTest {
	public static void main(String... args) {
		ScheduleTree scheduleTree = new ScheduleTree();
		
		ScheduleDto schedule1 = new ScheduleDto();
		schedule1.setScheduleId(87);
		schedule1.setSubjId(14);
		schedule1.setSubjectTitle("physics");
		schedule1.setLessonType("practise");
		schedule1.setTeacher(new TeacherDto(1, "Bob Markly"));
		schedule1.setClassroom("G432");
		
		ScheduleDto schedule2 = new ScheduleDto();
		schedule2.setScheduleId(3);
		schedule2.setSubjId(18);
		schedule2.setSubjectTitle("math");
		schedule2.setLessonType("lection");
		schedule2.setTeacher(new TeacherDto(4, "Daniel Kolin"));
		schedule2.setClassroom("D521");
		
		scheduleTree.add("monday", 1, ScheduleUtil.WEEK_UP, schedule1);
		// the schedule will replaced with new one, because path is the same
		scheduleTree.add("monday", 1, ScheduleUtil.WEEK_UP, schedule2);
		
		System.out.println(scheduleTree);
		
		ScheduleDto foundElem = scheduleTree.get("monday", 1, ScheduleUtil.WEEK_UP);
		System.out.println(foundElem);
	}
}
