package com.vitgon.schedule.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitgon.schedule.converter.util.CreateScheduleDTOAuxiliary;
import com.vitgon.schedule.dto.CreateScheduleDTO;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.util.LessonUtil;
import com.vitgon.schedule.util.ScheduleUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CreateScheduleDTOConverter extends AbstractHttpMessageConverter<CreateScheduleDTO> {
	
	private GroupService groupService;
	private SubjectService subjectService;
	private UserService userService;
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private ObjectMapper mapper = new ObjectMapper();
	
	public CreateScheduleDTOConverter(ApplicationContext context) {
		super(new MediaType("application","json"));
		groupService = context.getBean(GroupService.class);
		subjectService = context.getBean(SubjectService.class);
		userService = context.getBean(UserService.class);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return CreateScheduleDTO.class.isAssignableFrom(clazz);
	}

	@Override
	protected CreateScheduleDTO readInternal(Class<? extends CreateScheduleDTO> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String requestBody = IOUtils.toString(inputMessage.getBody(), DEFAULT_CHARSET);
		
		CreateScheduleDTOAuxiliary createScheduleDTOAuxiliary = new CreateScheduleDTOAuxiliary();
		try {
			createScheduleDTOAuxiliary = mapper.readValue(requestBody, CreateScheduleDTOAuxiliary.class);
		} catch (IOException e) {
			log.error("Can't parse JSON to EditScheduleDTO!", e);
		}
		
		User user = null;
		Group group = groupService.findById(createScheduleDTOAuxiliary.getGroupId());
		Subject subject = subjectService.findById(createScheduleDTOAuxiliary.getSubjectId());
		
		// For creating a schedule we need to know unique columns
		// from {@link com.vitgon.schedule.model.Schedule}
		// "group_id", "day_num", "week_type", "lesson_num"
		// and NOT_NULL field "subject_id"
		validate(
			group,
			createScheduleDTOAuxiliary.getDayNum(),
			createScheduleDTOAuxiliary.getWeek(),
			createScheduleDTOAuxiliary.getLessonNum(),
			subject
		);
		
		
		// if teacher (user) was specified then get him from database
		int userId = createScheduleDTOAuxiliary.getUserId();
		if (userId != 0) {
			user = userService.findById(userId);
		}
		
		CreateScheduleDTO editScheduleDTO = new CreateScheduleDTO();
		editScheduleDTO.setGroup(group);
		editScheduleDTO.setWeek(createScheduleDTOAuxiliary.getWeek());
		editScheduleDTO.setDayNum(createScheduleDTOAuxiliary.getDayNum());
		editScheduleDTO.setLessonNum(createScheduleDTOAuxiliary.getLessonNum());
		
		editScheduleDTO.setSubject(subject);
		
		if (LessonUtil.isValid(createScheduleDTOAuxiliary.getLessonType())) {
			editScheduleDTO.setLessonType(createScheduleDTOAuxiliary.getLessonType());
		}
		
		editScheduleDTO.setUser(user);
		
		if (createScheduleDTOAuxiliary.getClassroom() != null && !createScheduleDTOAuxiliary.getClassroom().equals("")) {
			editScheduleDTO.setClassroom(createScheduleDTOAuxiliary.getClassroom());
		} else {
			editScheduleDTO.setClassroom(null);
		}
		
		return editScheduleDTO;
	}

	@Override
	protected void writeInternal(CreateScheduleDTO t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		try {
			OutputStream outputStream = outputMessage.getBody();
			String body = mapper.writeValueAsString(t);
			outputStream.write(body.getBytes());
			outputStream.close();
		} catch (IOException e) {
			log.error("Can't parse JSON to EditScheduleDTO!", e);
		}
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
