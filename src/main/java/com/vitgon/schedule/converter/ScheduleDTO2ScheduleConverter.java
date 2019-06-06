package com.vitgon.schedule.converter;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.dto.EditScheduleDto;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.util.LessonUtil;
import com.vitgon.schedule.util.ScheduleUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScheduleDTO2ScheduleConverter implements Converter<EditScheduleDto, Schedule> {

    private GroupService groupService;
    private SubjectService subjectService;
    private UserService userService;

    @Override
    public Schedule convert(EditScheduleDto dtoObject) {
        Optional<Group> group = groupService.findById(dtoObject.getGroupId());
        Optional<Subject> subject = subjectService.findById(dtoObject.getSubjectId());

        // For creating a schedule we need to know unique columns
        // from {@link com.vitgon.schedule.model.Schedule}
        // "group_id", "day_num", "week_type", "lesson_num"
        // and NOT_NULL field "subject_id"
        validate(
                group.get(),
                dtoObject.getDayNum(),
                dtoObject.getWeek(),
                dtoObject.getLessonNum(),
                subject.get()
        );


        // if teacher (user) was specified then get him from database
        int userId = dtoObject.getUserId();
        Optional<User> user = null;
        if (userId != 0) {
            user = userService.findById(userId);
        }

        Schedule editScheduleDTO = new Schedule();
        editScheduleDTO.setGroup(group.get());
        editScheduleDTO.setWeek(dtoObject.getWeek());
        editScheduleDTO.setDayNum(dtoObject.getDayNum());
        editScheduleDTO.setLessonNum(dtoObject.getLessonNum());

        editScheduleDTO.setSubject(subject.get());

        if (LessonUtil.isValid(dtoObject.getLessonType())) {
            editScheduleDTO.setLessonType(LessonUtil.convertLessonType(dtoObject.getLessonType()));
        }

        editScheduleDTO.setUser(user.get());

        if (dtoObject.getClassroom() != null && !dtoObject.getClassroom().equals("")) {
            editScheduleDTO.setClassroom(dtoObject.getClassroom());
        } else {
            editScheduleDTO.setClassroom(null);
        }

        return editScheduleDTO;
    }

    public void validate(Group group, int dayNum, String weekType, int lessonNum, Subject subject) {
        // ckeck if group exists
        if (group == null) {
            throw new IllegalArgumentException("Group was not found!");
        }

        // check if day number was specified
        if (dayNum == 0) {
            throw new IllegalArgumentException("Day number was not found!");
        }

        // check if week type was specified
        if (!weekType.equals(ScheduleUtil.WEEK_UP) &&
                !weekType.equals(ScheduleUtil.WEEK_DOWN)) {
            throw new IllegalArgumentException("Week type was not found!");
        }

        // check if lesson number was specified
        if (lessonNum == 0) {
            throw new IllegalArgumentException("Lesson number was not found!");
        }

        // check if subject exists
        if (subject == null) {
            throw new IllegalArgumentException("Subject was not found!");
        }
    }
}
