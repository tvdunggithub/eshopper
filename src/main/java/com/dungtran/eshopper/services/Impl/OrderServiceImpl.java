package com.dungtran.eshopper.services.Impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.dao.OrderDao;
import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao od;

	@Override
	public void save(Order order) {
		od.save(order);		
	}

	@Override
	public Order findByUserAndDate(User user, Date date) {
		return od.findByUserAndDate(user, date);
	}

	@Override
	public List<Order> findByUser(User user) {		
		return od.findByUser(user);
	}

	

}
