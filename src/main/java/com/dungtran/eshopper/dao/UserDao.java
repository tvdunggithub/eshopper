package com.dungtran.eshopper.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dungtran.eshopper.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
	
	public Boolean existsByUsername(String username);

}
