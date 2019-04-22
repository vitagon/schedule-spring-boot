package com.vitgon.schedule.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import com.vitgon.schedule.model.database.auth.Role;


public class UserDetailsImpl implements UserDetails, SocialUserDetails {

	private static final long serialVersionUID = -2252279745523903046L;
	
	private String id;
	private String username;
	private String password;
	
	private String firstName;
	private String lastName;
	private List<SimpleGrantedAuthority> authorities;
	
	public UserDetailsImpl(String id, String username, String password, String firstName, String lastName, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = convertToAuthorities(roles);
	}

	public UserDetailsImpl(String username, String password, String firstName, String lastName, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = convertToAuthorities(roles);
	}
	
	public List<SimpleGrantedAuthority> convertToAuthorities(Set<Role> roles) {
		List<SimpleGrantedAuthority> auths = new ArrayList<>();
		for (Role role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return auths;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUserId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
