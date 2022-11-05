package com.dungtran.eshopper.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.dungtran.eshopper.config.CustomOAuth2User;
import com.dungtran.eshopper.entities.User;

@Service
@Transactional
public interface UserService {
	
	public Boolean createUser(@Valid User user) throws Exception;

	public User getUserByLogin(String login);
	
	public User getLoginUser(User user,CustomOAuth2User oauth2);
	
}
