package com.dungtran.eshopper.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.dao.ProductDao;
import com.dungtran.eshopper.entities.CartItem;
import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;

@Service
@Transactional
public interface CartItemService {
	
	public List<CartItem> findByUser(User user);
	
	public int addToCart(int id,int quantity,User user);
	
	public List<CartItem> findByOrderAndUser(Order order,User user);
	
	public int countSubtotal(Order order,User user);
	
	public int countTotal(Order order,User user);
	
	public void deleteByOrderAndProduct(Order order,Product product,User user);
	
	public int countByOrderAndUser(Order order,User user);
	
	public void save(CartItem cartItem);
	
}
