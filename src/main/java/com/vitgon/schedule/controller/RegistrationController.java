package com.vitgon.schedule.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.util.MessageUtil;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;

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
		User userExists = userService.findByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult.rejectValue("email", "Duplicate.email");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("auth/register");
		} else {
			userService.save(user);
			modelAndView.addObject("signUpSuccess", true);
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("auth/register");
		}
		
		return modelAndView;
	}
}
