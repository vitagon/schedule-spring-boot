package com.vitgon.schedule.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.service.database.impl.MajorServiceImpl;


public class MajorServiceImplTest {
	
	private MajorDao majorDao = Mockito.mock(MajorDao.class);
	private MajorTranslationDao majorTranslationDao = Mockito.mock(MajorTranslationDao.class);
	private MajorService majorService = new MajorServiceImpl(majorDao, majorTranslationDao);
	
	@Before
	public void setUp() {
		Major major = new Major();
		major.setUrl("economics_security");
		major.setDuration(5);
		
		Mockito.when(majorDao.findByUrl(major.getUrl())).thenReturn(major);
	}
	
	@Test
	public void whenValidUrl_thenMajorShouldBeFound() {
		String url = "economics_security";
		Major found = majorService.findByUrl(url);
		assertThat(found.getUrl()).isEqualTo(url);
	}
}
