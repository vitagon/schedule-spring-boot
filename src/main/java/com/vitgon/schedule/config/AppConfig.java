package com.vitgon.schedule.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vitgon.schedule.interceptor.UrlLocaleInterceptor;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	
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

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();

		registry.addInterceptor(localeInterceptor).addPathPatterns("/en/**", "/ru/**");
	}
}
