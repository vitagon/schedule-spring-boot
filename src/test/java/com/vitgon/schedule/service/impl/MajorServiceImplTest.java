package com.vitgon.schedule.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.service.MajorService;

@RunWith(SpringRunner.class)
public class MajorServiceImplTest {

	@TestConfiguration
	static class MajorServiceImplTestContextConfiguration {
		
		@Bean
		public MajorService majorService() {
			return new MajorServiceImpl();
		}
	}
	
	@Autowired
	private MajorService majorService;
	
	@MockBean
	private MajorDao majorDao;
	
	@MockBean
	private MajorTranslationDao majorTranslationDao;
	
	@Before
	public void setUp() {
		Major major = new Major("economics_security",5,null);
		
		Mockito.when(majorDao.findByUrl(major.getUrl())).thenReturn(major);
	}
	
	@Test
	public void whenValidUrl_thenMajorShouldBeFound() {
		String url = "economics_security";
		Major found = majorService.findByUrl(url);
		assertThat(found.getUrl()).isEqualTo(url);
	}
}
