package com.vitgon.schedule.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitgon.schedule.dto.CreateScheduleDTO;
import com.vitgon.schedule.dto.EditScheduleDTO;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditScheduleDTOConverter extends AbstractHttpMessageConverter<EditScheduleDTO> {
	
	private SubjectService subjectService;
	private UserService userService;
	
	public EditScheduleDTOConverter() {
	}

	public EditScheduleDTOConverter(SubjectService subjectService,
									UserService userService) {
		this.subjectService = subjectService;
		this.userService = userService;
	}
	
	@NoArgsConstructor
	@Data
	private class EditScheduleDTOAuxiliary implements Serializable {
		private static final long serialVersionUID = 7274534115141402913L;
		
		private int scheduleId;
		
		private int subjectId;
		private String lessonType;
		private int userId;
		private String classroom;
	}
	
	
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return EditScheduleDTO.class.isAssignableFrom(clazz);
	}

	@Override
	protected EditScheduleDTO readInternal(Class<? extends EditScheduleDTO> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String requestBody = toString(inputMessage.getBody());
		
		ObjectMapper mapper = new ObjectMapper();
		EditScheduleDTOAuxiliary editScheduleDTOAuxiliary = null;
		try {
			editScheduleDTOAuxiliary = mapper.readValue(requestBody, EditScheduleDTOAuxiliary.class);
		} catch (IOException e) {
			log.error("Can't parse String to EditScheduleDTO!", e);
		}
		
		// Required properties for editing are:
		// "scheduleId" and "subjectId"
		checkIfNotNull(editScheduleDTOAuxiliary.getScheduleId());
		Subject subject = subjectService.findById(editScheduleDTOAuxiliary.getSubjectId());
		checkIfNotNull(subject);
		
		User user = null;
		int userId = editScheduleDTOAuxiliary.getUserId();
		if (userId != 0) {
			user = userService.findById(userId);
		}
		
		EditScheduleDTO editScheduleDTO = new EditScheduleDTO();
		editScheduleDTO.setScheduleId(editScheduleDTOAuxiliary.getScheduleId());
		editScheduleDTO.setSubject(subject);
		editScheduleDTO.setLessonType(editScheduleDTOAuxiliary.getLessonType());
		editScheduleDTO.setUser(user);
		
		if (editScheduleDTOAuxiliary.getClassroom() != null && !editScheduleDTOAuxiliary.getClassroom().equals("")) {
			editScheduleDTO.setClassroom(editScheduleDTOAuxiliary.getClassroom());
		} else {
			editScheduleDTO.setClassroom(null);
		}
		
		return editScheduleDTO;
	}

	@Override
	protected void writeInternal(EditScheduleDTO t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			OutputStream outputStream = outputMessage.getBody();
			String body = mapper.writeValueAsString(t);
			outputStream.write(body.getBytes());
			outputStream.close();
		} catch (IOException e) {
			log.error("Can't parse String to EditScheduleDTO!", e);
		}
	}
	
	private void checkIfNotNull(Subject subject) {
		if (subject == null) {
			throw new IllegalArgumentException("Subject was not found!");
		}
	}
	
	private void checkIfNotNull(int scheduleId) {
		if (scheduleId <= 0) {
			throw new IllegalArgumentException("ScheduleId is not valid. Must be greater than 0!");
		}
	}
	
	private static String toString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		String result = scanner.useDelimiter("\\A").next();
		scanner.close();
		return result;
	}
}
