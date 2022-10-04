package com.dungtran.eshopper.services.Impl;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.dao.UserDao;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao ud;

	@Override
	public Boolean createUser(User user) throws Exception  {
		if (ud.existsByUsername(user.getUsername()))
			throw new Exception();
		else {
			ud.save(user);
			return true;
		}
	}

}
