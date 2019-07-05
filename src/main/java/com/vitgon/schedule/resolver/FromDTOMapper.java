package com.vitgon.schedule.resolver;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitgon.schedule.annotation.FromDTO;

@Component
public class FromDTOMapper extends RequestResponseBodyMethodProcessor {

	@Autowired
	private ConversionService conversionService;

	public FromDTOMapper(ObjectMapper objectMapper) {
		super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(FromDTO.class);
	}

	@Override
	protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		binder.validate();
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		FromDTO fromDTOAnnotation = parameter.getParameterAnnotation(FromDTO.class);
		Class<?> dtoClass = fromDTOAnnotation.value();
		Class<?> convertToType = parameter.getParameterType();
		return conversionService.convert(dtoClass.cast(dto), convertToType);
	}

	@Override
	protected <T> Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter parameter,
			Type paramType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
		for (Annotation ann : parameter.getParameterAnnotations()) {
			FromDTO fromDTO = AnnotationUtils.getAnnotation(ann, FromDTO.class);
			if (fromDTO != null) {
				return super.readWithMessageConverters(webRequest, parameter, fromDTO.value()); 
			}
		}
		throw new RuntimeException();
	}
}
