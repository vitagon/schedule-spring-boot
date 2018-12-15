package com.vitgon.schedule.controller.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainPageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testMainPageContoller() throws Exception {
		this.mockMvc.perform(get("/en/"))
			.andExpect(status().isOk())
			.andExpect(view().name("mainPage"));
	}
}
