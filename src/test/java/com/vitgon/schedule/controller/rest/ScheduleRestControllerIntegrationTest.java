package com.vitgon.schedule.controller.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitgon.schedule.dto.ScheduleDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ScheduleRestControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}
	
	@Test
	public void testCreateMethod() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/api/schedule/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createScheduleDTOForScheduleCreation())
				.cookie(new Cookie("user_lang", "en"));
		
		this.mockMvc.perform(builder)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", notNullValue()))
			.andExpect(jsonPath("$.subjectId", is(4)))
			.andExpect(jsonPath("$.subjectTitle", notNullValue()))
			.andExpect(jsonPath("$.dayNum", is(1)))
			.andExpect(jsonPath("$.week", is("up")))
			.andExpect(jsonPath("$.lessonNum", is(1)))
			.andExpect(jsonPath("$.teacherId", is(1)))
			.andExpect(jsonPath("$.teacherName", notNullValue()))
			.andExpect(jsonPath("$.lessonType", is("lecture")))
			.andExpect(jsonPath("$.lessonTypeName", is("Lecture")))
			.andExpect(jsonPath("$.classroom", is("L2391")))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testEditMethod() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/api/schedule/edit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createScheduleDTOForScheduleEdit())
				.cookie(new Cookie("user_lang", "en"));
		
		this.mockMvc.perform(builder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", notNullValue()))
			.andExpect(jsonPath("$.subjectId", is(4)))
			.andExpect(jsonPath("$.subjectTitle", notNullValue()))
			.andExpect(jsonPath("$.dayNum", is(1)))
			.andExpect(jsonPath("$.week", is("up")))
			.andExpect(jsonPath("$.lessonNum", is(1)))
			.andExpect(jsonPath("$.teacherId", is(1)))
			.andExpect(jsonPath("$.teacherName", notNullValue()))
			.andExpect(jsonPath("$.lessonType", is("practice")))
			.andExpect(jsonPath("$.lessonTypeName", is("Practice")))
			.andExpect(jsonPath("$.classroom", is("P2251")))
			.andDo(MockMvcResultHandlers.print());
	}
	
	private static String createScheduleDTOForScheduleCreation() throws JsonProcessingException {
		ScheduleDto scheduleDTO = new ScheduleDto();
		
		scheduleDTO.setGroupId(1);
		scheduleDTO.setWeek("up");
		scheduleDTO.setDayNum(1);
		scheduleDTO.setLessonNum(1);
		
		scheduleDTO.setSubjectId(4);
		scheduleDTO.setLessonType("lecture");
		scheduleDTO.setUserId(1);
		scheduleDTO.setClassroom("L2391");
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(scheduleDTO);
	}
	
	private static String createScheduleDTOForScheduleEdit() throws JsonProcessingException {
		ScheduleDto scheduleDTO = new ScheduleDto();
		
		scheduleDTO.setGroupId(1);
		scheduleDTO.setWeek("up");
		scheduleDTO.setDayNum(1);
		scheduleDTO.setLessonNum(1);
		
		scheduleDTO.setSubjectId(4);
		scheduleDTO.setLessonType("practice");
		scheduleDTO.setUserId(1);
		scheduleDTO.setClassroom("P2251");
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(scheduleDTO);
	}
}
