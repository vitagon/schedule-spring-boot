package com.vitgon.schedule.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.dao.auth.UserDao;
import com.vitgon.schedule.model.auth.CustomUserDetails;
import com.vitgon.schedule.model.auth.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		final CustomUserDetails userDetails = new CustomUserDetails(
				user.getEmail(),
				user.getPassword(),
				user.getKeyFirstname(),
				user.getKeyLastname(),
				user.getRoles()
		);
		return userDetails;
	}

}
