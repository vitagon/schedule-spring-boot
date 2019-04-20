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
	private String email;
	private String password;
	
	private String firstName;
	private String lastName;
	private List<SimpleGrantedAuthority> authorities;
	
	public UserDetailsImpl(String id, String email, String password, String firstName, String lastName, Set<Role> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = convertToAuthorities(roles);
	}

	public UserDetailsImpl(String email, String password, String firstName, String lastName, Set<Role> roles) {
		this.email = email;
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
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
