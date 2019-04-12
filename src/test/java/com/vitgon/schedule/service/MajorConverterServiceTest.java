package com.vitgon.schedule.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.dto.MajorDtoControl;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.MajorService;

public class MajorConverterServiceTest {
	
	private MajorService majorService = mock(MajorService.class);
	private MajorTitleService majorTitleService = mock(MajorTitleService.class);
	private LocaleService localeService = mock(LocaleService.class);
	
	private MajorConverterService majorConverterService;

	@Before
	public void init() {
		majorConverterService = spy(new MajorConverterService(majorService, majorTitleService, localeService));
	}
	
	@Test
	public void testConvertAllToDtoListMethod() {
		List<Major> majorList = createMajorList();
		when(majorService.findAll()).thenReturn(majorList);
		List<MajorDtoControl> majorDtoControlList = majorConverterService.convertToMajorDtoControlList();
		
		assertNotNull(majorDtoControlList);
		assertFalse(majorDtoControlList.isEmpty());
		assertEquals(1, majorDtoControlList.get(0).getId());
		assertEquals("management", majorDtoControlList.get(0).getName());
		assertEquals(2, majorDtoControlList.get(1).getId());
		assertEquals("banking", majorDtoControlList.get(1).getName());
		verify(majorService, times(1)).findAll();
	}
	
	@Test
	public void testConvertAllToDtoListMethodWithLocaleObject() {
		Locale locale = mock(Locale.class);
		List<Major> majorList = createMajorList();
		when(majorService.findAll()).thenReturn(majorList);
		when(majorTitleService.getMajorTitle(locale, majorList.get(0))).thenReturn("менеджмент");
		when(majorTitleService.getMajorTitle(locale, majorList.get(1))).thenReturn("банковское дело");
		
		List<MajorDtoControl> majorDtoControlList = majorConverterService.convertToMajorDtoControlList(locale);
		
		assertNotNull(majorDtoControlList);
		assertFalse(majorDtoControlList.isEmpty());
		assertEquals(1, majorDtoControlList.get(0).getId());
		assertEquals("менеджмент", majorDtoControlList.get(0).getName());
		assertEquals(2, majorDtoControlList.get(1).getId());
		assertEquals("банковское дело", majorDtoControlList.get(1).getName());
		verify(majorService, times(1)).findAll();
		verify(majorTitleService, times(2)).getMajorTitle(any(Locale.class), any(Major.class));
	}
	
	@Test
	public void testConvertAllToDtoListMethodWithLocaleId() {
		when(localeService.findById(any(int.class))).thenReturn(new Locale());
		when(majorConverterService.convertToMajorDtoControlList(any(Locale.class))).thenReturn(new ArrayList<>());
		
		List<MajorDtoControl> majorDtoControlList = majorConverterService.convertToMajorDtoControlList(1);
		
		assertNotNull(majorDtoControlList);
		verify(localeService, times(1)).findById(any(int.class));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConvertAllToDtoListMethodThrowsExceptionIfLocaleWasNotFound() {
		when(localeService.findById(any(int.class))).thenReturn(null);
		majorConverterService.convertToMajorDtoControlList(1);
	}
	
	private List<Major> createMajorList() {
		List<Major> majorList = new ArrayList<>();
		
		Major major1 = new Major();
		major1.setId(1);
		major1.setName("management");
		
		Major major2 = new Major();
		major2.setId(2);
		major2.setName("banking");
		
		majorList.add(major1);
		majorList.add(major2);
		return majorList;
	}
}
