package com.dungtran.eshopper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.dao.UserDao;
import com.dungtran.eshopper.entities.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user!=null) {
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}
	
	

}
