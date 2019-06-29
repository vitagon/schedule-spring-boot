package com.vitgon.schedule.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.service.database.LocaleService;

@RunWith(PowerMockRunner.class)
public class LocaleDtoServiceTest {
	
	private LocaleService localeService;
	private LocaleDtoService localeMapperService;

	@Before
	public void init() {
		localeService = Mockito.mock(LocaleService.class);
		localeMapperService = new LocaleDtoService(localeService);
	}
	
//	@PrepareForTest({LocaleUtil.class})
//	@Test
//	public void testMapLocalesToListMethod() {
//		List<LocaleDto> localeDtoList = localeDtoList();
//		int localeId = localeDtoList.get(0).getId();
//		String code = localeDtoList.get(0).getCode();
//		List<Locale> locales = Arrays.asList(new Locale("en"));
//		
//		when(localeService.findAll()).thenReturn(locales);
//		
//		PowerMockito.mockStatic(LocaleUtil.class);
//		PowerMockito.when(LocaleUtil.mapToLocaleDTOList(Arrays.asList(new Locale("en"))))
//			.thenReturn(localeDtoList);
//		
//		List<LocaleDto> localeDtoListResult = localeMapperService.mapLocalesToList();
//		Assert.assertEquals(localeId, localeDtoListResult.get(0).getId());
//		Assert.assertEquals(code, localeDtoListResult.get(0).getCode());
//		
//		BDDMockito.verify(localeService, Mockito.times(1)).findAll();
//		
//		PowerMockito.verifyStatic(LocaleUtil.class, Mockito.times(1));
//		LocaleUtil.mapToLocaleDTOList(locales);
//	}
	
	private List<LocaleDto> localeDtoList() {
		return Arrays.asList(new LocaleDto(1, "en"));
	}
}
