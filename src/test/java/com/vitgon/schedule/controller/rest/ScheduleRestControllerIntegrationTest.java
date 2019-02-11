package com.vitgon.schedule.controller.rest;

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
import com.vitgon.schedule.dto.ScheduleDTO;
import com.vitgon.schedule.model.Subject;

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
	public void testScheduleRestController() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/api/schedule/edit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createEditScheduleDTO())
				.cookie(new Cookie("user_lang", "ru"));
		
		this.mockMvc.perform(builder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}
	
	private static String createEditScheduleDTO() throws JsonProcessingException {
		ScheduleDTO editScheduleDTO = new ScheduleDTO();
		
		editScheduleDTO.setSubjectId(1);
		
		editScheduleDTO.setClassroom("G999");
		editScheduleDTO.setLessonType("lecture");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(editScheduleDTO);
	}
}
