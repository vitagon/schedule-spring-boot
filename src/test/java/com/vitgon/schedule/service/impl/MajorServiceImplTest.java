package com.vitgon.schedule.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.service.database.impl.MajorServiceImpl;


public class MajorServiceImplTest {
	
	private MajorDao majorDao = Mockito.mock(MajorDao.class);
	private MajorTranslationDao majorTranslationDao = Mockito.mock(MajorTranslationDao.class);
	private LocaleConverterService localeConverterService = Mockito.mock(LocaleConverterService.class);
	private MajorService majorService = new MajorServiceImpl(majorDao, majorTranslationDao, localeConverterService);
	
	@Before
	public void setUp() {
		Major major = new Major();
		major.setUrl("economics_security");
		major.setDuration(5);
		
		Mockito.when(majorDao.findByUrl(major.getUrl())).thenReturn(Optional.of(major));
	}
	
	@Test
	public void whenValidUrl_thenMajorShouldBeFound() {
		String url = "economics_security";
		Optional<Major> found = majorService.findByUrl(url);
		assertThat(found.get().getUrl()).isEqualTo(url);
	}
}
