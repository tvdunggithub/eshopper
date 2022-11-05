package com.dungtran.eshopper.config;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OAuth2User user;
	
	
	public String getUsername() {
		return user.getAttribute("name");
	}
	
	public String getEmail() {
		return user.getAttribute("email");
	}


	@Override
	public Map<String, Object> getAttributes() {
		return user.getAttributes();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}


	@Override
	public String getName() {
		return user.getName();
	}
	

}
