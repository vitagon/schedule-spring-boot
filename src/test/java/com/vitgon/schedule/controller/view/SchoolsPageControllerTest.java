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

import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;


/**
 * Don't understand how to inject mocks in controller
 * and I don't need controller to retrieve data from database 
 * 
 * @author User
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolsPageControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Test
	public void testSchoolsPageController() throws Exception {
//		Locale locale = new Locale("en");
//		Map<Integer, Map<String,Object>> found = new HashMap<>();
//		Map<String,Object> subMap = new HashMap<>();
//		subMap.put("title", "school of economics and management");
//		found.put(1, subMap);
		
//		when(localeService.findByCode("en")).thenReturn(locale);
//		when(schoolService.findAllByLocale(locale)).thenReturn(found);
		
		this.mvc.perform(get("/{lang}/schools", "en"))
			.andExpect(status().isOk())
			.andExpect(view().name("schools"));
//			.andExpect(model().attribute("schools", found));
	}
}
