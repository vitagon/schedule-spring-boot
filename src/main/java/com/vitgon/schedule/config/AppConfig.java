package com.vitgon.schedule.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitgon.schedule.converter.SchoolId2SchoolConverter;
import com.vitgon.schedule.converter.StringToDaysEnumConverter;
import com.vitgon.schedule.converter.StringToDegreeEnumConverter;
import com.vitgon.schedule.converter.SubjectId2SubjectConverter;
import com.vitgon.schedule.formatter.DateFormatter;
import com.vitgon.schedule.interceptor.UrlLocaleInterceptor;
import com.vitgon.schedule.resolver.FromDTOMapper;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;

@Configuration
@ComponentScan(basePackages = { "com.vitgon.schedule" })
public class AppConfig implements WebMvcConfigurer {
	
	
	private ApplicationContext applicationContext;
	
	@Autowired
	public AppConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public SchoolService schoolService() {
		return this.applicationContext.getBean(SchoolService.class);
	}
	
	public GroupService groupService() {
		return this.applicationContext.getBean(GroupService.class);
	}
	
	public SubjectService subjectService() {
		return this.applicationContext.getBean(SubjectService.class);
	}
	
	public UserService userService() {
		return this.applicationContext.getBean(UserService.class);
	}
	
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		LocaleResolver resolver = new UrlLocaleResolver();
		return resolver;
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

		// Read i18n/messages_xxx.properties file.
		// For example: i18n/messages_en.properties
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource);
		return validatorFactoryBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();
		registry.addInterceptor(localeInterceptor).addPathPatterns("/**");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new DateFormatter());
		registry.addConverter(new SubjectId2SubjectConverter(subjectService()));
		registry.addConverter(new SchoolId2SchoolConverter(schoolService()));
		registry.addConverter(new StringToDegreeEnumConverter());
		registry.addConverter(new StringToDaysEnumConverter());
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(applicationContext).build();
		resolvers.add(new FromDTOMapper(objectMapper, applicationContext));
	}
}
