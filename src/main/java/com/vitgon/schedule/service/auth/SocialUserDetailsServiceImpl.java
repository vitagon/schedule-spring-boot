package com.vitgon.schedule.service.auth;

import com.vitgon.schedule.model.UserDetailsImpl;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
	
	private UserService userService;

	public SocialUserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		SocialUserDetails socialUserDetails = new UserDetailsImpl(
				user.getId().toString(),
				user.getUsername(),
				user.getPassword(),
				user.getKeyFirstname(),
				user.getKeyLastname(),
				user.getRoles());
		return socialUserDetails;
	}
}
