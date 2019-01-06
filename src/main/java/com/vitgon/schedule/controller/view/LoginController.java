package com.vitgon.schedule.controller.view;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("auth/login");
		return modelAndView;
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
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult
				.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("auth/register");
		} else {
			userService.save(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("auth/register");
		}
		
		return modelAndView;
	}
}
