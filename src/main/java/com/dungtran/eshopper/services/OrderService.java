package com.dungtran.eshopper.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.User;

@Service
@Transactional
public interface OrderService {
	
	public void save(Order order);
	
	public Order findByUserAndDate(User user,Date date);
	
	public List<Order> findByUser(User user);
}
