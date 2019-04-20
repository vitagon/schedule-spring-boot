package com.vitgon.schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SpringSocialConfigurer;

import com.vitgon.schedule.handler.CustomAccessDeniedHandler;
import com.vitgon.schedule.service.auth.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// For standard authentication
		// auth
		//	.jdbcAuthentication()
		//	.usersByUsernameQuery(usersQuery)
		//	.authoritiesByUsernameQuery(rolesQuery)
		//	.dataSource(dataSource)
		//	.passwordEncoder(bCryptPasswordEncoder);
		
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return authProvider;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		
		http
			.authorizeRequests()
//				.antMatchers("/control/**").hasAnyAuthority("MANAGER", "ADMIN")
				.antMatchers("/").permitAll()
				.and()
				.csrf().disable()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
			.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler());
		http.apply(new SpringSocialConfigurer()).signupUrl("/signup");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
