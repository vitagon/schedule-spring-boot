package com.vitgon.schedule.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vitgon.schedule.annotation.test.IntegrationTest;


@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolsPageControllerIT {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testSchoolsPageController() throws Exception {
		this.mvc.perform(get("/schools", "en"))
			.andExpect(status().isOk())
			.andExpect(view().name("schools"))
			.andExpect(model().attributeExists("schools"));
	}
}
