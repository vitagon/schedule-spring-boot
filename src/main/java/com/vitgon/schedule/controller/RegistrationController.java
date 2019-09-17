package com.vitgon.schedule.controller;

import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;

@Controller
public class RegistrationController {

	private UserService userService;
	private HttpServletRequest request;

	public RegistrationController(UserService userService, HttpServletRequest request) {
		this.userService = userService;
		this.request = request;
	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject(user);
		modelAndView.setViewName("auth/register");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		Locale locale = (Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByEmailAndProviderId(user.getEmail(), "local");
		
		if (userExists != null) {
			bindingResult.rejectValue("email", "Duplicate.email");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("auth/register");
		} else {
			user.setUsername(UUID.randomUUID().toString().substring(0, 16));
			user.setProviderId("local");
			user = userService.save(user);
			
			user.setUsername("user"+user.getId());
			userService.update(user);
			modelAndView.addObject("signUpSuccess", true);
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("auth/register");
		}
		
		return modelAndView;
	}
}
