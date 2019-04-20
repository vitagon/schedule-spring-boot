package com.vitgon.schedule.service.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.UserDetailsImpl;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
	
	private UserService userService;

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		User user = userService.findById(Integer.parseInt(userId));
		SocialUserDetails socialUserDetails = new UserDetailsImpl(
				user.getId().toString(),
				user.getEmail(),
				user.getPassword(),
				user.getKeyFirstname(),
				user.getKeyLastname(),
				user.getRoles());
		return socialUserDetails;
	}
}
