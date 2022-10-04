package com.dungtran.eshopper.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.User;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	
	public Order findByUserAndDate(User user,Date date);
	
	public List<Order> findByUser(User user);


}
