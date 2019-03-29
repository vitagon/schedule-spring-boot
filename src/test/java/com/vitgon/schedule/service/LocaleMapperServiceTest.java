package com.vitgon.schedule.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.util.LocaleUtil;

@RunWith(PowerMockRunner.class)
public class LocaleMapperServiceTest {
	
	private LocaleService localeService;
	private LocaleMapperService localeMapperService;

	@Before
	public void init() {
		localeService = Mockito.mock(LocaleService.class);
		localeMapperService = new LocaleMapperService(localeService);
	}
	
	@PrepareForTest({LocaleUtil.class})
	@Test
	public void testMapLocalesToListMethod() {
		List<LocaleDto> localeDtoList = localeDtoList();
		int localeId = localeDtoList.get(0).getId();
		String code = localeDtoList.get(0).getCode();
		List<Locale> locales = Arrays.asList(new Locale("en"));
		
		when(localeService.findAll()).thenReturn(locales);
		
		PowerMockito.mockStatic(LocaleUtil.class);
		PowerMockito.when(LocaleUtil.mapToLocaleDTOList(Arrays.asList(new Locale("en"))))
			.thenReturn(localeDtoList);
		
		List<LocaleDto> localeDtoListResult = localeMapperService.mapLocalesToList();
		Assert.assertEquals(localeId, localeDtoListResult.get(0).getId());
		Assert.assertEquals(code, localeDtoListResult.get(0).getCode());
		
		BDDMockito.verify(localeService, Mockito.times(1)).findAll();
		
		PowerMockito.verifyStatic(LocaleUtil.class, Mockito.times(1));
		LocaleUtil.mapToLocaleDTOList(locales);
	}
	
	private List<LocaleDto> localeDtoList() {
		return Arrays.asList(new LocaleDto(1, "en"));
	}
}
