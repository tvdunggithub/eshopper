package com.dungtran.eshopper.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.dungtran.eshopper.entities.User;

@Service
@Transactional
public interface UserService {
	
	public Boolean createUser(@Valid User user) throws Exception;
}
