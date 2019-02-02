package com.vitgon.schedule.collection;

import com.vitgon.schedule.pojo.SchedulePOJO;
import com.vitgon.schedule.pojo.TeacherPOJO;
import com.vitgon.schedule.util.ScheduleUtil;

public class ScheduleTreeTest {
	public static void main(String... args) {
		ScheduleTree scheduleTree = new ScheduleTree();
		
		SchedulePOJO schedule1 = new SchedulePOJO();
		schedule1.setScheduleId(87);
		schedule1.setSubjId(14);
		schedule1.setSubjectTitle("physics");
		schedule1.setLessonType("practise");
		schedule1.setTeacher(new TeacherPOJO(1, "Bob Markly"));
		schedule1.setClassroom("G432");
		
		SchedulePOJO schedule2 = new SchedulePOJO();
		schedule2.setScheduleId(3);
		schedule2.setSubjId(18);
		schedule2.setSubjectTitle("math");
		schedule2.setLessonType("lection");
		schedule2.setTeacher(new TeacherPOJO(4, "Daniel Kolin"));
		schedule2.setClassroom("D521");
		
		scheduleTree.add("monday", 1, ScheduleUtil.WEEK_UP, schedule1);
		// the schedule will replaced with new one, because path is the same
		scheduleTree.add("monday", 1, ScheduleUtil.WEEK_UP, schedule2);
		
		System.out.println(scheduleTree);
		
		SchedulePOJO foundElem = scheduleTree.get("monday", 1, ScheduleUtil.WEEK_UP);
		System.out.println(foundElem);
	}
}
